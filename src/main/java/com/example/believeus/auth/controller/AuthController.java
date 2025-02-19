package com.example.believeus.auth.controller;

import com.example.believeus.auth.dto.RegisterRequestDTO;
import com.example.believeus.auth.dto.RegisterResponseDTO;
import com.example.believeus.auth.application.AuthService;
import com.example.believeus.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth API", description = "공통 인증/인가 API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "아이디 및 비밀번호 등록", description = "사용자가 아이디 및 비밀번호를 입력하여 기본 계정을 생성합니다. 이후 추가 정보를 입력하여 최종 회원가입을 완료해야 합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "ID/PW 입력이 완료되었으며 추가 정보 입력이 필요합니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "중복된 아이디 또는 전화번호"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponseDTO>> registerUser(@RequestBody RegisterRequestDTO request) {
        RegisterResponseDTO response = authService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(response, "아이디와 비밀번호가 등록되었습니다. 추가 정보를 입력하여 회원가입을 완료하세요."));
    }
}
