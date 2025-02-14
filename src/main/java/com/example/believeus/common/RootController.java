package com.example.believeus.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/")
public class RootController {

    @GetMapping
    public Map<String, String> welcome() {
        return Map.of(
                "message", "Welcome to BelieveUs API!",
                "version", "1.0.0",
                "status", "running",
                "docs", "/swagger-ui.html"
        );
    }
}