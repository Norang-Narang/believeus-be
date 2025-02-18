package com.example.believeus.senior.controller;

import com.example.believeus.common.ApiResponse;
import com.example.believeus.senior.Senior;
import com.example.believeus.senior.dto.SeniorRequestDTO;
import com.example.believeus.senior.dto.SeniorResponseDTO;
import com.example.believeus.senior.application.SeniorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Admin API", description = "관리자 API")
@RestController
@RequestMapping("/api/v1/seniors")
@RequiredArgsConstructor
public class SeniorController {
    private final SeniorService seniorService;

    @Operation(
            summary = "어르신 정보 등록",
            description = "관리자가 어르신 정보를 등록하는 API입니다. ADMIN 권한이 필요합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "어르신 정보 등록 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "권한 없음"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<SeniorResponseDTO>> registerSenior(@RequestBody SeniorRequestDTO request) {
        Senior senior = seniorService.registerSenior(request);
        SeniorResponseDTO responseDTO = SeniorResponseDTO.fromEntity(senior);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(responseDTO, "어르신 정보가 등록되었습니다."));
    }
}
