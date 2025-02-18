package com.example.believeus.auth.application;

import com.example.believeus.auth.JwtTokenProvider;
import com.example.believeus.auth.domain.RefreshToken;
import com.example.believeus.auth.dto.LoginRequest;
import com.example.believeus.auth.dto.LoginResponse;
import com.example.believeus.caregiver.domain.Caregiver;
import com.example.believeus.caregiver.repository.CaregiverRepository;
import com.example.believeus.admin.domain.Admin;
import com.example.believeus.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CaregiverRepository caregiverRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    public LoginResponse login(LoginRequest request) {
        Optional<Caregiver> caregiver = caregiverRepository.findByUsername(request.getUsername());
        Optional<Admin> admin = adminRepository.findByUsername(request.getUsername());

        // 요양보호사 로그인 로직
        if (caregiver.isPresent() && passwordEncoder.matches(request.getPassword(), caregiver.get().getPassword())) {
            String accessToken = jwtTokenProvider.generateAccessToken(caregiver.get().getUsername(), "ROLE_CAREGIVER");
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(caregiver.get().getUsername());
            return new LoginResponse(accessToken, refreshToken.getToken());
        }

        // 관리자 로그인 로직
        if (admin.isPresent() && passwordEncoder.matches(request.getPassword(), admin.get().getPassword())) {
            String accessToken = jwtTokenProvider.generateAccessToken(admin.get().getUsername(), "ROLE_ADMIN");
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(admin.get().getUsername());
            return new LoginResponse(accessToken, refreshToken.getToken());
        }

        // 로그인 실패 (아이디 또는 비밀번호 불일치)
        throw new IllegalArgumentException("아이디 또는 비밀번호가 잘못되었습니다.");
    }

    public String getUserRole(String username) {
        if (caregiverRepository.findByUsername(username).isPresent()) {
            return "ROLE_CAREGIVER";
        }
        if (adminRepository.findByUsername(username).isPresent()) {
            return "ROLE_ADMIN";
        }
        throw new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
    }

    // Access Token 생성
    public String generateAccessToken(String username, String role) {
        return jwtTokenProvider.generateAccessToken(username, role);
    }
}
