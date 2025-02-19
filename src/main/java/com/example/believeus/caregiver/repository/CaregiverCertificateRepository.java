package com.example.believeus.caregiver.repository;

import com.example.believeus.caregiver.domain.CaregiverCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaregiverCertificateRepository extends JpaRepository<CaregiverCertificate, Long> {
}
