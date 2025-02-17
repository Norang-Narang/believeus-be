package com.example.believeus.auth.application;

import com.example.believeus.auth.JwtTokenProvider;
import com.example.believeus.auth.domain.RefreshToken;
import com.example.believeus.auth.repository.RefreshTokenRepository;
import com.example.believeus.caregiver.repository.CaregiverRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final CaregiverRepository caregiverRepository;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    // 리프레시 토큰 생성 및 저장
    @Transactional
    public RefreshToken createRefreshToken(String username) {
        // 기존 토큰이 있으면 삭제
        refreshTokenRepository.deleteByUsername(username);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsername(username);
        refreshToken.setToken(jwtTokenProvider.generateRefreshToken(username));
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenExpiration));

        return refreshTokenRepository.save(refreshToken);
    }

    // 리프레시 토큰 검증
    public Optional<RefreshToken> verifyToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .map(rt -> {
                    if (rt.getExpiryDate().isBefore(Instant.now().minusSeconds(1))) {
                        return null;
                    }
                    return rt;
                });
    }

    // 리프레시 토큰 삭제 (로그아웃)
    @Transactional
    public void deleteByUsername(String username) {
        refreshTokenRepository.deleteByUsername(username);
    }
}
