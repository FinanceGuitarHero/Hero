package com.example.financeservice.repository;

import com.example.financeservice.entity.Balance;
import com.example.financeservice.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    List<Balance> findAllByUserId(Integer userId);
    List<Balance> findAllByBank(Bank bank);
    List<Balance> findByUserId(Integer id);

    Optional<Balance> findByBankAndUserId(Bank bank, Integer userId);
}
