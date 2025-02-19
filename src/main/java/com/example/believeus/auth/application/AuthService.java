package com.example.believeus.auth.application;

import com.example.believeus.auth.JwtTokenProvider;
import com.example.believeus.auth.domain.AccountStatus;
import com.example.believeus.auth.domain.Role;
import com.example.believeus.auth.domain.User;
import com.example.believeus.auth.dto.LoginRequestDTO;
import com.example.believeus.auth.dto.LoginResponseDTO;
import com.example.believeus.auth.dto.RegisterRequestDTO;
import com.example.believeus.auth.dto.RegisterResponseDTO;
import com.example.believeus.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RegisterResponseDTO registerUser(RegisterRequestDTO request) {
        // PENDING 상태의 계정이 있는지 확인
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());

        if (existingUser.isPresent()) {
            User user = existingUser.get();

            if (user.getStatus() == AccountStatus.COMPLETED) {
                throw new IllegalArgumentException("이미 가입된 아이디입니다.");
            } else {
                // PENDING 계정이 있으면 기존 계정 활용
                return new RegisterResponseDTO(user.getId(), user.getUsername());
            }
        }

        // 중복 가입 방지
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("이미 가입된 아이디입니다.");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);
        return new RegisterResponseDTO(user.getId(), user.getUsername());
    }

    @Transactional
    public LoginResponseDTO authenticate(LoginRequestDTO request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디 또는 비밀번호입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 아이디 또는 비밀번호입니다.");
        }

        // JWT Access & Refresh 토큰 생성
        String accessToken = jwtTokenProvider.generateAccessToken(user.getUsername(), user.getRole().name());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getUsername(), user.getRole().name());

        return new LoginResponseDTO(accessToken, refreshToken, user.getRole().name());
    }
}
