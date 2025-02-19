package com.example.believeus.caregiver.domain;

import com.example.believeus.auth.domain.User;
import com.example.believeus.util.Location;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "caregivers")
public class Caregiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 20, unique = true)
    private String phoneNumber;

    @ElementCollection
    @CollectionTable(name = "caregiver_locations", joinColumns = @JoinColumn(name = "caregiver_id"))
    private List<Location> locations = new ArrayList<>();

    private boolean hasVehicle;
    private boolean hasDementiaTraining;
    private String experienceYears;
    private String majorExperience;
    private String introduction;
    private String profileImageUrl;

    @OneToMany(mappedBy = "caregiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaregiverCertificate> certificates = new ArrayList<>();

    public static Caregiver createCaregiver(User user, String name, String phoneNumber, boolean hasVehicle,
                                            boolean hasDementiaTraining, String experienceYears, String majorExperience,
                                            String introduction, String profileImageUrl, List<Location> locations) {
        if (locations.size() > 3) {
            throw new IllegalArgumentException("최대 3개의 지역만 등록할 수 있습니다.");
        }
        return Caregiver.builder()
                .user(user)
                .name(name)
                .phoneNumber(phoneNumber)
                .hasVehicle(hasVehicle)
                .hasDementiaTraining(hasDementiaTraining)
                .experienceYears(experienceYears)
                .majorExperience(majorExperience)
                .introduction(introduction)
                .profileImageUrl(profileImageUrl)
                .locations(locations)
                .build();
    }

    // 지역 추가 메서드 (최대 3개 제한)
    public void addLocation(Location location) {
        if (this.locations.size() >= 3) {
            throw new IllegalStateException("최대 3개의 지역만 설정할 수 있습니다.");
        }
        this.locations.add(location);
    }
}
