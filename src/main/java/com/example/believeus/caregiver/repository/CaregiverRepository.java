package com.example.believeus.caregiver.repository;

import com.example.believeus.caregiver.domain.Caregiver;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CaregiverRepository extends JpaRepository<Caregiver, Long> {
    Optional<Caregiver> findByUsername(String username);
    boolean existsByUsername(String username);
}