package com.example.believeus.auth.application;

import com.example.believeus.auth.domain.Role;
import com.example.believeus.auth.domain.User;
import com.example.believeus.auth.dto.RegisterRequestDTO;
import com.example.believeus.auth.dto.RegisterResponseDTO;
import com.example.believeus.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RegisterResponseDTO registerUser(RegisterRequestDTO request) {
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
        return new RegisterResponseDTO(user.getId());
    }
}
