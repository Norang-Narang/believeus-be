package com.example.believeus.auth.application;

import com.example.believeus.caregiver.domain.Caregiver;
import com.example.believeus.caregiver.repository.CaregiverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final CaregiverRepository caregiverRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 아이디(username)로 사용자 찾기
        Caregiver caregiver = caregiverRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

        // UserDetails 객체 반환
        return User.builder()
                .username(caregiver.getUsername())
                .password(caregiver.getPassword())
                .roles("USER")  // 기본 권한 부여
                .build();
    }
}