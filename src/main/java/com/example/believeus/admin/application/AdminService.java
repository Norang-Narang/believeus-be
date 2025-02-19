package com.example.believeus.admin.application;

import com.example.believeus.admin.domain.Admin;
import com.example.believeus.admin.dto.AdminDetailsRequestDTO;
import com.example.believeus.admin.repository.AdminRepository;
import com.example.believeus.auth.domain.Role;
import com.example.believeus.auth.domain.User;
import com.example.believeus.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

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
}
