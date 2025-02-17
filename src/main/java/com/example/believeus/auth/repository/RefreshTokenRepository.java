package com.example.believeus.auth.repository;

import com.example.believeus.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);       // 리프레시 토큰 조회
    void deleteByUsername(String username);     // 사용자 로그아웃 시 기존 토큰 삭제
}
