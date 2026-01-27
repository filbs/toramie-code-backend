package com.toramie.storemanager.controller;

import com.toramie.storemanager.dto.CalculatorRequest;
import com.toramie.storemanager.service.CalculatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping ("/api/public/calculator")
public class CalculatorController {
    @Autowired
    private CalculatorService service;

    public CalculatorController (CalculatorService service) {
        this.service = service;
    }

    @PostMapping ("/calculate")
    public ResponseEntity<BigDecimal> calculate (@RequestBody @Valid CalculatorRequest request) {
        BigDecimal result = service.calculateTotal(request);
        return ResponseEntity.ok(result);
    }

}
