package com.example.financeservice.service;


import com.example.financeservice.entity.Bank;
import com.example.financeservice.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public Optional<Bank> getBankById(Integer id) {
        return bankRepository.findById(id);
    }

    public Bank createBank(Bank bank) {
        return bankRepository.save(bank);
    }

    public Bank updateBank(Integer id, Bank bankDetails) {
        Bank bank = bankRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bank not found with id: " + id));
        bank.setBankName(bankDetails.getBankName());
        bank.setBankCode(bankDetails.getBankCode());
        bank.setName(bankDetails.getName());
        return bankRepository.save(bank);
    }

    public void deleteBank(Integer id) {
        Bank bank = bankRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bank not found with id: " + id));
        bankRepository.delete(bank);
    }
}
