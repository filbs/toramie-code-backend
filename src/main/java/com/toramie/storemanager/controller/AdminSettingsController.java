package com.toramie.storemanager.controller;

import com.toramie.storemanager.model.CalculatorSettings;
import com.toramie.storemanager.service.CalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/settings")
public class AdminSettingsController {
    private final CalculatorService calculatorService;

    public AdminSettingsController (CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/check")
    public ResponseEntity<Void> check () {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<CalculatorSettings> getAdminSettings () {
        return ResponseEntity.ok(calculatorService.getSettings());
    }

    @PutMapping
    public ResponseEntity<CalculatorSettings> setAdminSettings (@RequestBody CalculatorSettings settings) {
        CalculatorSettings updated = calculatorService.updateSettings(settings);
        return ResponseEntity.ok(updated);
    }
}
