package com.example.believeus.senior;

import com.example.believeus.admin.domain.Admin;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Senior {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("노인 ID") private Long id;
    @Comment("노인 이름") private String name;
    @Comment("생년월일") private LocalDate birthDate;
    public enum Gender {
        MALE, // 남
        FEMALE // 여
    }
    @Comment("성별") private Gender gender;
    public enum CareGrade {
        FIRST, // 1등급
        SECOND,  // 2등급
        THIRD, // 3등급
        FOURTH, // 4등급
        FIFTH, // 5등급
        COGNITIVE_SUPPORT // 인지지원등급
    }
    @Comment("장기요양등급") private CareGrade careGrade;
    @Comment("주소") private String address;

    @Comment("케어 필요 항목") private String careNeeds;
    @CreatedDate @Comment("등록 일자") private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id") @Comment("관리자 ID")
    private Admin admin;

    @Builder
    public Senior(String name, LocalDate birthDate, Gender gender, CareGrade careGrade, String address, String careNeeds, Admin admin) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.careGrade = careGrade;
        this.address = address;
        this.careNeeds = careNeeds;
        this.admin = admin;
    }
}
