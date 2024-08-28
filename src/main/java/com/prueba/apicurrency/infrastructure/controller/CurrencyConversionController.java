package com.prueba.apicurrency.infrastructure.controller;

import com.prueba.apicurrency.aplication.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping("/api")
public class CurrencyConversionController {
    @Autowired
    private CurrencyConversionService conversionService;

    @GetMapping("/convert")
    public ResponseEntity<Map<String, Object>> convertCurrency(
            @RequestParam String sourceCurrency,
            @RequestParam String targetCurrency,
            @RequestParam Double amount) {
        Map<String, Object> result = conversionService.convertCurrency(sourceCurrency, targetCurrency, amount);
        return ResponseEntity.ok(result);
    }
}
