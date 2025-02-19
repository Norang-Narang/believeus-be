package com.example.believeus.admin.application;

import com.example.believeus.admin.domain.Admin;
import com.example.believeus.admin.dto.AdminDetailsRequestDTO;
import com.example.believeus.admin.dto.SeniorListResponse;
import com.example.believeus.admin.repository.AdminRepository;
import com.example.believeus.auth.domain.Role;
import com.example.believeus.auth.domain.User;
import com.example.believeus.auth.repository.UserRepository;
import com.example.believeus.caregiver.domain.Caregiver;
import com.example.believeus.caregiver.repository.CaregiverRepository;
import com.example.believeus.senior.Senior;
import com.example.believeus.senior.dto.SeniorUpdateRequest;
import com.example.believeus.senior.repository.SeniorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final SeniorRepository seniorRepository;
    private final CaregiverRepository caregiverRepository;

    @Transactional
    public void registerAdminDetails(AdminDetailsRequestDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 역할 업데이트
        user.updateRole(Role.ROLE_ADMIN);
        user.completeRegistration();
        userRepository.save(user);

        Admin admin = Admin.createAdmin(
                user,
                request.getName(),
                request.getCenterName(),
                request.getPhone(),
                request.getAddress(),
                request.getCenterGrade(),
                request.getHasBathVehicle(),
                request.getOperatingPeriod(),
                request.getIntroduction(),
                request.getProfileImageUrl()
        );

        adminRepository.save(admin);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseEntity<List<SeniorListResponse>> seniors() {
        List<Senior> seniors = seniorRepository.findAll();

        // Senior 객체를 SeniorListResponse로 변환
        List<SeniorListResponse> response = seniors.stream()
                .map(senior -> SeniorListResponse.builder()
                        .name(senior.getName())
                        .age(senior.getAge() + "세")
                        .gender(senior.getGender() == Senior.Gender.MALE ? "남" : "여")
                        .build()).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseEntity<SeniorListResponse> senior(Long seniorId) {
        Senior senior = seniorRepository.findById(seniorId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 어르신입니다"));

        SeniorListResponse response = SeniorListResponse
                .builder()
                .name(senior.getName())
                .age(senior.getAge() + "세")
                .gender(senior.getGender() == Senior.Gender.MALE ? "남" : "여")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> update(Long seniorId, SeniorUpdateRequest request) {
        Map<String, Object> response = new HashMap<>();
        Senior senior = seniorRepository.findById(seniorId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 어르신입니다"));
        senior.update(
                request.name(),
                request.age(),
                request.birthDate(),
                request.address(),
                request.height(),
                request.weight(),
                request.grade(),
                request.careNeeds()
        );
        seniorRepository.save(senior);
        response.put("message", "성공적으로 어르신 정보가 수정되었습니다");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // TODO : 아직 완성 안됨
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseEntity<?> caregiver(Long caregiverId) {
        Caregiver caregiver = caregiverRepository.findById(caregiverId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 요양보호사입니다"));

        return null;
    }
}
