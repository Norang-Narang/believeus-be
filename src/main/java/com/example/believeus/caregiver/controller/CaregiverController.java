package com.example.believeus.caregiver.controller;

import com.example.believeus.caregiver.application.CaregiverService;
import com.example.believeus.caregiver.dto.CaregiverSignupRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@Tag(name = "Caregiver API", description = "요양보호사 관련 API")
@RestController
@RequestMapping("/api/v1/caregivers")
@RequiredArgsConstructor
public class CaregiverController {

    private final CaregiverService caregiverService;

    @Operation(summary = "요양보호사 회원가입", description = "요양보호사용 회원가입 API")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
            @ApiResponse(responseCode = "409", description = "중복된 아이디 또는 전화번호"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody CaregiverSignupRequest request) {
        caregiverService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("회원가입이 완료되었습니다.");
    }
}
