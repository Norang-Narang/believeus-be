package com.example.believeus.auth.application;

import com.example.believeus.auth.JwtTokenProvider;
import com.example.believeus.auth.dto.LoginRequest;
import com.example.believeus.auth.dto.LoginResponse;
import com.example.believeus.caregiver.domain.Caregiver;
import com.example.believeus.caregiver.repository.CaregiverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CaregiverRepository caregiverRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(LoginRequest request) {
        // 유저 조회
        Caregiver caregiver = caregiverRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 잘못되었습니다."));

        // 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), caregiver.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 잘못되었습니다.");
        }

        // JWT 토큰 생성
        String accessToken = jwtTokenProvider.generateAccessToken(caregiver.getUsername());
        String refreshToken = jwtTokenProvider.generateRefreshToken(caregiver.getUsername());

        return new LoginResponse(accessToken, refreshToken);
    }
}