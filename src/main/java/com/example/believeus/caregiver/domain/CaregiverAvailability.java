package com.example.believeus.caregiver.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CaregiverAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caregiver_id")
    private Caregiver caregiver;

    @ElementCollection
    private List<String> availableHours;
}
