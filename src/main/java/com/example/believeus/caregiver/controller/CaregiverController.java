package com.example.believeus.caregiver.controller;

import com.example.believeus.caregiver.application.CaregiverService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Caregiver API", description = "요양보호사 API")
@RestController
@RequestMapping("/api/v1/caregiver")
@RequiredArgsConstructor
public class CaregiverController {
    private final CaregiverService caregiverService;
}
