package com.example.believeus.caregiver.controller;

import com.example.believeus.caregiver.dto.CaregiverDetailsRequestDTO;
import com.example.believeus.caregiver.application.CaregiverService;
import com.example.believeus.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Caregiver API", description = "요양보호사 API")
@RestController
@RequestMapping("/api/v1/caregiver")
@RequiredArgsConstructor
public class CaregiverController {
    private final CaregiverService caregiverService;

    @Operation(summary = "요양보호사 회원가입 (추가 정보 등록)", description = "회원가입 후 요양보호사의 추가 정보를 입력합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
    })
    @PostMapping("/details")
    public ResponseEntity<ApiResponse<Void>> registerCaregiverDetails(@RequestBody CaregiverDetailsRequestDTO request) {
        caregiverService.registerCaregiverDetails(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(null, "요양보호사 정보가 등록되었습니다."));
    }
}
