package com.example.financeservice.repository;

import com.example.financeservice.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    Optional<Currency> findByCurrency_code(String CurrencyCode);
}
