package com.example.financeservice.service;

import com.example.financeservice.entity.*;
import com.example.financeservice.service.*;
import com.example.financeservice.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    private static final String EXCHANGE_RATE_API_URL = "https://v6.exchangerate-api.com/v6/faa9720162c45c55f7dac6ab/latest/";

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Optional<Currency> getCurrencyByCode(String code) {
        return currencyRepository.findByCurrency_code(code);
    }

    public Currency createCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    public Currency updateCurrency(String code, Currency currencyDetails) {
        Currency currency = currencyRepository.findByCurrency_code(code)
                .orElseThrow(() -> new IllegalArgumentException("Currency not found with code: " + code));
        currency.setCurrencyName(currencyDetails.getCurrencyName());
        currency.setSymbol(currencyDetails.getSymbol());
        return currencyRepository.save(currency);
    }

    public void deleteCurrency(String code) {
        Currency currency = currencyRepository.findByCurrency_code(code)
                .orElseThrow(() -> new IllegalArgumentException("Currency not found with code: " + code));
        currencyRepository.delete(currency);
    }
}
