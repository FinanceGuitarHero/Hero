package com.example.financeservice.controller;

import com.example.financeservice.entity.*;
import com.example.financeservice.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping("/user/{id}")
    public List<Balance> getUserBalances(@PathVariable Integer id) {
        return balanceService.getUserBalances(id);
    }

    @GetMapping
    public List<Balance> getAllBalances() {
        return balanceService.getAllBalances();
    }

    @GetMapping("/{balanceId}")
    public List<Balance> getBalanceById(@PathVariable Integer balanceId) {
        return balanceService.getBalanceById(balanceId);
    }

    @GetMapping("/bank/{bankId}")
    public List<Balance> getBalanceByBankId(@PathVariable Integer bankId) {
        return balanceService.getBalancesByBankId(bankId);
    }

    @GetMapping("/{userId}/{bankId}/{currencyCode}")
    public Optional<Balance> getBalanceByUserIdAndBankId(@PathVariable Integer userId, @PathVariable Integer bankId) {
        return balanceService.getBalanceByUserIdAndBankId(userId, bankId);
    }

    @PostMapping("/create/{userId}/{bankId}/{currencyCode}")
    public void createNewBalance(@PathVariable Integer userId, @PathVariable Integer bankId, @PathVariable String currencyCode) {
        balanceService.createNewBalance(userId, bankId, currencyCode);
    }

    @DeleteMapping("/{userId}/{bankId}/{currencyCode}")
    public void deleteBalanceByUserIdAndBankIdAndCurrencyCode(@PathVariable Integer userId, @PathVariable Integer bankId, @PathVariable String currencyCode) {
        balanceService.deleteBalance(userId, bankId, currencyCode);
    }

}
