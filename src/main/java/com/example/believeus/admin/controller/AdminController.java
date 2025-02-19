package com.example.believeus.admin.controller;

import com.example.believeus.admin.application.AdminService;
import com.example.believeus.admin.dto.SeniorListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Admin API", description = "관리자 API")
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @Operation(summary = "전체 어르신 목록 조회", description = "전체 어르신의 정보를 가져옵니다")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/seniors")
    public ResponseEntity<List<SeniorListResponse>> getSeniors() {
        return adminService.seniors();
    }
}
