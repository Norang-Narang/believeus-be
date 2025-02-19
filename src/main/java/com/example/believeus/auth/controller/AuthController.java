package com.example.believeus.auth.controller;

import com.example.believeus.auth.JwtTokenProvider;
import com.example.believeus.auth.application.AuthService;
import com.example.believeus.auth.dto.LoginRequestDTO;
import com.example.believeus.auth.dto.LoginResponseDTO;
import com.example.believeus.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth API", description = "로그인/인증 API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "로그인", description = "JWT Access/Refresh 토큰을 발급합니다.")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = authService.authenticate(request);
        return ResponseEntity.ok(ApiResponse.success(response, "로그인 성공"));
    }
}
