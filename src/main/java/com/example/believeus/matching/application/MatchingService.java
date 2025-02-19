package com.example.believeus.matching.application;

import com.example.believeus.caregiver.domain.Caregiver;
import com.example.believeus.caregiver.domain.QCaregiver;
import com.example.believeus.caregiver.domain.QCaregiverAvailability;
import com.example.believeus.caregiver.domain.QCaregiverSkill;
import com.example.believeus.matching.dto.CaregiverMatchDTO;
import com.example.believeus.matching.dto.MatchingResponseDTO;
import com.example.believeus.senior.Senior;
import com.example.believeus.senior.repository.SeniorRepository;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchingService {
    private final JPAQueryFactory queryFactory;
    private final SeniorRepository seniorRepository;

    public List<MatchingResponseDTO> getMatchingCaregivers(Long seniorId) {
        List<CaregiverMatchDTO> matches = findMatchingCaregivers(seniorId);

        return matches.stream()
                .map(match -> new MatchingResponseDTO(
                        match.getCaregiverId(),
                        match.getName(),
                        match.getPhoneNumber(),
                        Integer.parseInt(String.valueOf(match.getExperienceYears())),  // experienceYears 변환 추가
                        match.isHasVehicle(),
                        match.isHasDementiaTraining(),
                        match.getMatchingScore()
                ))
                .collect(Collectors.toList());
    }

    private List<CaregiverMatchDTO> findMatchingCaregivers(Long seniorId) {
        Senior senior = seniorRepository.findById(seniorId)
                .orElseThrow(() -> new IllegalArgumentException("해당 노인이 존재하지 않습니다."));

        QCaregiver caregiver = QCaregiver.caregiver;
        QCaregiverAvailability availability = QCaregiverAvailability.caregiverAvailability;
        QCaregiverSkill skill = QCaregiverSkill.caregiverSkill;

        List<Tuple> results = queryFactory
                .select(caregiver, calculateMatchingScore(senior, caregiver, availability, skill))
                .from(caregiver)
                .leftJoin(availability).on(availability.caregiver.eq(caregiver))
                .leftJoin(skill).on(skill.caregiver.eq(caregiver))
                .where(
                        locationFilter(senior, caregiver),
                        timeFilter(senior, availability),
                        skillFilter(senior, skill)
                )
                .orderBy(calculateMatchingScore(senior, caregiver, availability, skill).desc())
                .fetch();

        return results.stream()
                .map(tuple -> {
                    Caregiver caregiverEntity = tuple.get(caregiver); // Caregiver 객체 전체 가져오기
                    Integer matchingScore = tuple.get(1, Integer.class); // 매칭 점수

                    return new CaregiverMatchDTO(
                            caregiverEntity.getId(),
                            caregiverEntity.getName(),
                            caregiverEntity.getPhoneNumber(),
                            caregiverEntity.getProfileImageUrl(),
                            caregiverEntity.getLocations().isEmpty() ? null : caregiverEntity.getLocations().get(0),
                            Integer.parseInt(caregiverEntity.getExperienceYears()),
                            caregiverEntity.isHasVehicle(),
                            caregiverEntity.isHasDementiaTraining(),
                            matchingScore
                    );
                })
                .collect(Collectors.toList());

    }

    // 지역 필터링
    private BooleanExpression locationFilter(Senior senior, QCaregiver caregiver) {
        return caregiver.locations.any().city.eq(senior.getLocation().getCity())
                .or(caregiver.locations.any().district.eq(senior.getLocation().getDistrict()))
                .or(caregiver.locations.any().neighborhood.eq(senior.getLocation().getNeighborhood()));
    }

    // 근무시간 필터링
    private BooleanExpression timeFilter(Senior senior, QCaregiverAvailability availability) {
        return availability.availableHours.any().in(senior.getRequestedTime());
    }

    // 케어 필요항목 필터링
    private BooleanExpression skillFilter(Senior senior, QCaregiverSkill skill) {
        return skill.skillName.in(senior.getRequiredCareSkills());
    }

    // 매칭 점수 계산
    private NumberExpression<Integer> calculateMatchingScore(Senior senior, QCaregiver caregiver,
                                                             QCaregiverAvailability availability, QCaregiverSkill skill) {
        return Expressions.numberTemplate(Integer.class,
                        "CASE " +
                                "WHEN {0} THEN 20 " + // 같은 동
                                "WHEN {1} THEN 15 " + // 같은 구
                                "WHEN {2} THEN 10 " + // 같은 시
                                "END",
                        caregiver.locations.any().neighborhood.eq(senior.getLocation().getNeighborhood()),
                        caregiver.locations.any().district.eq(senior.getLocation().getDistrict()),
                        caregiver.locations.any().city.eq(senior.getLocation().getCity()))
                .add(
                        Expressions.numberTemplate(Integer.class,
                                "CASE " +
                                        "WHEN {0} THEN 30 " +  // 근무 시간 완전 일치
                                        "WHEN {1} THEN 15 " +  // 일부 겹침
                                        "END",
                                availability.availableHours.any().in(senior.getRequestedTime()),
                                availability.availableHours.any().in(senior.getRequestedTime())
                        )
                )
                .add(
                        Expressions.numberTemplate(Integer.class,
                                "CASE " +
                                        "WHEN {0} = {1} THEN 40 " +  // 100% 일치
                                        "WHEN {0} >= {1} * 0.5 THEN 25 " +  // 50% 이상 일치
                                        "WHEN {0} < {1} * 0.5 THEN 10 " +  // 50% 미만 일치
                                        "END",
                                skill.skillName.count(),
                                senior.getRequiredCareSkills().size()
                        )
                )
                .add(
                        Expressions.numberTemplate(Integer.class,
                                "{0} * 3 + {1} * 5",
                                Expressions.numberTemplate(Integer.class, "CAST({0} AS INTEGER)", caregiver.experienceYears).goe(5),
                                caregiver.hasDementiaTraining.when(true).then(1).otherwise(0)
                        )
                );
    }
}
