package com.example.believeus.auth.controller;

import com.example.believeus.auth.dto.RegisterRequestDTO;
import com.example.believeus.auth.dto.RegisterResponseDTO;
import com.example.believeus.auth.application.AuthService;
import com.example.believeus.caregiver.dto.CaregiverDetailsRequestDTO;
import com.example.believeus.caregiver.application.CaregiverService;
import com.example.believeus.admin.dto.AdminDetailsRequestDTO;
import com.example.believeus.admin.application.AdminService;
import com.example.believeus.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Register API", description = "회원가입 API")
@RestController
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
public class RegisterController {
    private final AuthService authService;
    private final AdminService adminService;
    private final CaregiverService caregiverService;

    @Operation(summary = "아이디 및 비밀번호 등록", description = "회원가입의 첫 단계로 아이디와 비밀번호를 저장합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<RegisterResponseDTO>> registerUser(@RequestBody RegisterRequestDTO request) {
        RegisterResponseDTO response = authService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(response, "ID/PW 입력이 완료되었습니다. 추가 정보를 입력하여 계정 등록을 완료하세요."));
    }

    @Operation(summary = "관리자 추가 정보 등록", description = "관리자의 추가 정보를 입력하여 회원가입을 완료합니다.")
    @PostMapping("/admin")
    public ResponseEntity<ApiResponse<Void>> registerAdmin(@RequestBody AdminDetailsRequestDTO request) {
        adminService.registerAdminDetails(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(null, "관리자 정보가 등록되었습니다."));
    }

    @Operation(summary = "요양보호사 추가 정보 등록", description = "요양보호사의 추가 정보를 입력하여 회원가입을 완료합니다.")
    @PostMapping("/caregiver")
    public ResponseEntity<ApiResponse<Void>> registerCaregiver(@RequestBody CaregiverDetailsRequestDTO request) {
        caregiverService.registerCaregiverDetails(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(null, "요양보호사 정보가 등록되었습니다."));
    }
}
