package com.example.financeservice.repository;

import com.example.financeservice.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Integer> {
    boolean existsByName(String name);

    Optional<Bank> findByName(String name);
}
