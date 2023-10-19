package com.flama.tictactoe.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/health-check")
    public ResponseEntity<String> verify() {
        return ResponseEntity.ok().body("OK");
    }
}
