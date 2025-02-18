package com.example.believeus.senior.controller;

import com.example.believeus.common.ApiResponse;
import com.example.believeus.senior.Senior;
import com.example.believeus.senior.dto.SeniorRequestDTO;
import com.example.believeus.senior.dto.SeniorResponseDTO;
import com.example.believeus.senior.application.SeniorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seniors")
@RequiredArgsConstructor
public class SeniorController {
    private final SeniorService seniorService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<SeniorResponseDTO>> registerSenior(@RequestBody SeniorRequestDTO request) {
        Senior senior = seniorService.registerSenior(request);
        SeniorResponseDTO responseDTO = SeniorResponseDTO.fromEntity(senior);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(responseDTO, "어르신 정보가 등록되었습니다."));
    }
}
