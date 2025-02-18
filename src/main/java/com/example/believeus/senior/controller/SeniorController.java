package com.example.believeus.senior.controller;

import com.example.believeus.senior.Senior;
import com.example.believeus.senior.dto.SeniorRequestDTO;
import com.example.believeus.senior.application.SeniorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seniors")
@RequiredArgsConstructor
public class SeniorController {
    private final SeniorService seniorService;

    @PostMapping("/register")
    public ResponseEntity<Senior> registerSenior(@RequestBody SeniorRequestDTO request) {
        Senior senior = seniorService.registerSenior(request);
        return ResponseEntity.ok(senior);
    }
}
