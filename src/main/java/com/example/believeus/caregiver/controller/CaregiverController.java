package com.example.believeus.caregiver.controller;

import com.example.believeus.caregiver.application.CaregiverService;
import com.example.believeus.caregiver.dto.CaregiverSignupRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/caregivers")
@RequiredArgsConstructor
public class CaregiverController {

    private final CaregiverService caregiverService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody CaregiverSignupRequest request) {
        caregiverService.signUp(request);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }
}
