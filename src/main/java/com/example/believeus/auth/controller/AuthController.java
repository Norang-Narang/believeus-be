package com.example.believeus.auth.controller;

import com.example.believeus.auth.application.RefreshTokenService;
import com.example.believeus.auth.dto.LoginRequest;
import com.example.believeus.auth.dto.LoginResponse;
import com.example.believeus.auth.application.AuthService;
import com.example.believeus.auth.dto.RefreshTokenRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Common Auth API", description = "공통 인증/인가 API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @Operation(summary = "통합 로그인", description = "요양보호사/관리자 통합 로그인 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "403", description = "인증 실패"),
    })

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return refreshTokenService.verifyToken(request.getRefreshToken())
                .map(rt -> {
                    String newAccessToken = authService.generateAccessToken(rt.getUsername());
                    return ResponseEntity.ok(new LoginResponse(newAccessToken, rt.getToken()));
                })
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않거나 만료된 리프레시 토큰입니다."));
    }
}
