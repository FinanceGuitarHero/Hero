package com.example.financeservice.service;

import com.example.financeservice.entity.*;
import com.example.financeservice.repository.*;
import com.example.financeservice.dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BalanceService {

    @Autowired
    private BankRepository bankRepository;
    
    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired UserService userService;

    @Transactional
    public Optional<Balance> updateBalance(BalanceDTO balanceDTO) {
        Integer userId = balanceDTO.getUserId();
        Optional<Bank> bank = bankRepository.findById(balanceDTO.getBankId());

        if (bank.isPresent()) {

            Optional<Balance> optionalBalance = balanceRepository.findByBankAndUserId(bank.get(), userId);
            if (optionalBalance.isEmpty()) {
                throw new IllegalArgumentException("Balance not found for user_id: " + userId + ", bank_id: " + balanceDTO.getBankId());
            }

            Balance balance = optionalBalance.get();
            if (balanceDTO.getTransactionType() == 1) {
                balance.setAmount(balance.getAmount().add(balanceDTO.getValue()));
            } else if (balanceDTO.getTransactionType() == 2) {
                if (balance.getAmount().compareTo(balanceDTO.getValue()) < 0) {
                    throw new IllegalArgumentException("Insufficient funds");
                }
                balance.setAmount(balance.getAmount().subtract(balanceDTO.getValue()));
            }

            balance.setLast_updated(balanceDTO.getTime());
            balanceRepository.save(balance);
            return Optional.of(balance);
        } else {
            throw new IllegalArgumentException("Currency or Bank not found");
        }
    }

    public List<Balance> getUserBalances(Integer id){
        return balanceRepository.findAllByUserId(id);
    }

    

    public List<Balance> getAllBalances(){
        return balanceRepository.findAll();
    }
    
    public List<Balance> getBalancesByBankId(Integer bankId){
        Optional<Bank> bank = bankRepository.findById(bankId);
        return balanceRepository.findAllByBank(bank.get());
    }

    public void createNewBalance(String userId, Integer bankId, String currencyCode) {
        Bank bank = bankRepository.findById(bankId).get();
        Balance balance = new Balance();
        balance.setBank(bank);
        balance.setAppUserId(userService.getUserById(userId));
        balanceRepository.save(balance);
    }
    public void deleteBalance(Integer userId, Integer bankId, String currencyCode) {
        Optional<Bank> bank = bankRepository.findById(bankId);
        Optional<Balance> optionalBalance = balanceRepository.findByBankAndUserId(bank.get(), userId);
        if (optionalBalance.isEmpty()) {
            throw new IllegalArgumentException("Balance not found for user_id: " + userId + ", bank_id: " + bankId + ", currency_code: " + currencyCode);
        }
        Balance balance = optionalBalance.get();
        balanceRepository.delete(balance);
    }

    public List<Balance> getBalanceById(Integer balanceId) {
        return balanceRepository.findByUserId(balanceId);
    }

    public Optional<Balance> getBalanceByUserIdAndBankId(Integer userId, Integer bankId) {
        Optional<Bank> bank = bankRepository.findById(bankId);
        return balanceRepository.findByBankAndUserId(bank.get(), userId);
    }
}