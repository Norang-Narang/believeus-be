package com.example.believeus.senior.application;

import com.example.believeus.admin.domain.Admin;
import com.example.believeus.admin.repository.AdminRepository;
import com.example.believeus.senior.Senior;
import com.example.believeus.senior.dto.SeniorRequestDTO;
import com.example.believeus.senior.repository.SeniorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeniorService {

    private final SeniorRepository seniorRepository;
    private final AdminRepository adminRepository;

    @Transactional
    public Senior registerSenior(SeniorRequestDTO request) {
        Admin admin = adminRepository.findById(request.getAdminId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 관리자입니다."));

        Senior senior = Senior.builder()
                .name(request.getName())
                .birthDate(request.getBirthDate())
                .gender(request.getGender())
                .careGrade(request.getCareGrade())
                .address(request.getAddress())
                .height(request.getHeight())
                .weight(request.getWeight())
                .careNeeds(request.getCareNeeds())
                .admin(admin)
                .build();

        return seniorRepository.save(senior);
    }
}

