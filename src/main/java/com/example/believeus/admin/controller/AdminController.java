package com.example.believeus.admin.controller;

import com.example.believeus.admin.dto.AdminDetailsRequestDTO;
import com.example.believeus.admin.application.AdminService;
import com.example.believeus.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Admin API", description = "관리자 API")
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @Operation(summary = "관리자 회원가입 (추가 정보 등록)", description = "회원가입 후 관리자의 추가 정보를 입력합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "관리자 정보 등록 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
    })
    @PostMapping("/details")
    public ResponseEntity<ApiResponse<Void>> registerAdminDetails(@RequestBody AdminDetailsRequestDTO request) {
        adminService.registerAdminDetails(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(null, "관리자 정보가 등록되었습니다."));
    }
}
