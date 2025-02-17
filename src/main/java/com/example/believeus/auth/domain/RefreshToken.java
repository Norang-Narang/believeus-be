package com.example.believeus.auth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;       // Refresh Token 값

    @Column(nullable = false)
    private String username;        // 해당 토큰이 속한 사용자 ID

    @Column(nullable = false)
    private Instant expiryDate;     // 토큰 만료일
}
