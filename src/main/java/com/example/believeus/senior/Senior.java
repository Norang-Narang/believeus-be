package com.example.believeus.senior;

import com.example.believeus.admin.domain.Admin;
import com.example.believeus.util.Location;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Senior {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("노인 ID")
    private Long id;

    @Comment("노인 이름")
    private String name;

    @Comment("생년월일")
    private LocalDate birthDate;

    public enum Gender {
        MALE, // 남
        FEMALE // 여
    }

    @Enumerated(EnumType.STRING)
    @Comment("성별")
    private Gender gender;

    public enum CareGrade {
        FIRST, // 1등급
        SECOND,  // 2등급
        THIRD, // 3등급
        FOURTH, // 4등급
        FIFTH, // 5등급
        COGNITIVE_SUPPORT // 인지지원등급
    }

    @Enumerated(EnumType.STRING)
    @Comment("장기요양등급")
    private CareGrade careGrade;

    @Comment("주소")
    @Embedded
    private Location location;

    @ElementCollection
    @CollectionTable(name = "senior_requested_time", joinColumns = @JoinColumn(name = "senior_id"))
    @Column(name = "time")
    @Comment("요청한 근무 시간")
    private List<String> requestedTime = new ArrayList<>();  // ✅ 기본값 설정

    @ElementCollection
    @CollectionTable(name = "senior_required_care_skills", joinColumns = @JoinColumn(name = "senior_id"))
    @Column(name = "skill")
    @Comment("케어 필요 항목")
    private List<String> requiredCareSkills = new ArrayList<>();  // ✅ 기본값 설정

    @Comment("케어 필요 사항")
    @Column(nullable = false)
    private String careNeeds = "";  // ✅ 필드 추가 및 기본값 설정

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Comment("등록 일자")
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    @Comment("관리자 ID")
    private Admin admin;

    @Builder
    public Senior(String name, LocalDate birthDate, Gender gender, CareGrade careGrade, Location location,
                  List<String> requestedTime, List<String> requiredCareSkills, String careNeeds, Admin admin) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.careGrade = careGrade;
        this.location = location;
        this.requestedTime = requestedTime != null ? requestedTime : new ArrayList<>();  // ✅ 기본값 설정
        this.requiredCareSkills = requiredCareSkills != null ? requiredCareSkills : new ArrayList<>();  // ✅ 기본값 설정
        this.careNeeds = careNeeds != null ? careNeeds : "";  // ✅ 기본값 설정
        this.admin = admin;
    }
}
