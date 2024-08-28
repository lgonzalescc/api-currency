package com.prueba.apicurrency.aplication.service;

import com.prueba.apicurrency.domain.model.Conversion;
import com.prueba.apicurrency.domain.repository.ConversionRepository;
import com.prueba.apicurrency.infrastructure.client.ExchangeRateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyConversionService {
    @Autowired
    private ExchangeRateClient exchangeRateClient;

    @Autowired
    private ConversionRepository conversionRepository;

    @SuppressWarnings("unchecked")
    public Map<String, Object> convertCurrency(String sourceCurrency, String targetCurrency, Double amount) {
        Map<String, Object> response = exchangeRateClient.getExchangeRates();
        Map<String, Double> rates = (Map<String, Double>) response.get("rates");

        Double sourceRate = convertToDouble(rates.get(sourceCurrency));
        Double targetRate = convertToDouble(rates.get(targetCurrency));

        if (sourceRate == null || targetRate == null) {
            throw new IllegalArgumentException("Invalid currency code.");
        }

        Double exchangeRate = targetRate / sourceRate;
        Double convertedAmount = amount * exchangeRate;

        Conversion conversion = new Conversion();
        conversion.setSourceCurrency(sourceCurrency);
        conversion.setTargetCurrency(targetCurrency);
        conversion.setAmount(amount);
        conversion.setConvertedAmount(convertedAmount);
        conversion.setExchangeRate(exchangeRate);

        conversionRepository.save(conversion);

        Map<String, Object> result = new HashMap<>();
        result.put("amount", amount);
        result.put("convertedAmount", convertedAmount);
        result.put("sourceCurrency", sourceCurrency);
        result.put("targetCurrency", targetCurrency);
        result.put("exchangeRate", exchangeRate);

        return result;
    }

    private Double convertToDouble(Object value) {
        if (value instanceof Integer) {
            return ((Integer) value).doubleValue();
        } else if (value instanceof Double) {
            return (Double) value;
        } else {
            throw new IllegalArgumentException("Invalid type for exchange rate.");
        }
    }
}
