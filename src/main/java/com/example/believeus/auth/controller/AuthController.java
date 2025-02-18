package com.example.believeus.auth.controller;

import com.example.believeus.admin.repository.AdminRepository;
import com.example.believeus.auth.application.RefreshTokenService;
import com.example.believeus.auth.dto.LoginRequest;
import com.example.believeus.auth.dto.LoginResponse;
import com.example.believeus.auth.application.AuthService;
import com.example.believeus.auth.dto.RefreshTokenRequest;
import com.example.believeus.caregiver.repository.CaregiverRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Common Auth API", description = "공통 인증/인가 API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final CaregiverRepository caregiverRepository;
    private final AdminRepository adminRepository;

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

    @Operation(summary = "Access Token 갱신", description = "만료된 Access Token을 Refresh Token을 이용해 갱신하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "새로운 Access Token 발급 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "403", description = "유효하지 않거나 만료된 Refresh Token")
    })
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return refreshTokenService.verifyToken(request.getRefreshToken())
                .map(rt -> {
                    String role = authService.getUserRole(rt.getUsername());
                    String newAccessToken = authService.generateAccessToken(rt.getUsername(), role);
                    return ResponseEntity.ok(new LoginResponse(newAccessToken, rt.getToken()));
                })
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않거나 만료된 리프레시 토큰입니다."));
    }

    @Operation(summary = "로그아웃", description = "사용자의 Refresh Token을 무효화하여 로그아웃하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그아웃 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "403", description = "유효하지 않거나 만료된 Refresh Token")
    })
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody RefreshTokenRequest request) {
        refreshTokenService.deleteByRefreshToken(request.getRefreshToken());
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().body(Map.of("message", "정상적으로 로그아웃되었습니다. "));
    }
}
