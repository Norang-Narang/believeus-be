package com.example.believeus.admin.application;

import com.example.believeus.admin.domain.Admin;
import com.example.believeus.admin.dto.AdminSignupRequest;
import com.example.believeus.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerAdmin(AdminSignupRequest request) {
        if (adminRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        if (!request.getPassword().equals(request.getPasswordConfirm())) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        Admin admin = new Admin(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getName(),
                request.getPhoneNumber(),
                request.getCenterName(),
                request.getHasBathVehicle(),
                request.getAddress(),
                request.getCenterGrade(),
                request.getOperatingPeriod(),
                request.getIntroduction(),
                request.getProfileImageUrl()
        );

        adminRepository.save(admin);
    }
}
