package com.example.believeus.caregiver.repository;

import com.example.believeus.caregiver.domain.Caregiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaregiverRepository extends JpaRepository<Caregiver, Long> {
}