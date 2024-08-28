package com.prueba.apicurrency.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "exchangeRateClient", url = "https://open.er-api.com/v6/latest/USD")
public interface ExchangeRateClient {
    @GetMapping
    Map<String, Object> getExchangeRates();
}
