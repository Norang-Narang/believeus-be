package com.example.believeus.caregiver.controller;

import com.example.believeus.caregiver.application.CaregiverService;
import com.example.believeus.caregiver.dto.CaregiverSignupRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/caregivers")
@RequiredArgsConstructor
public class CaregiverController {

    private final CaregiverService caregiverService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@Valid @RequestBody CaregiverSignupRequest request) {
        caregiverService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
