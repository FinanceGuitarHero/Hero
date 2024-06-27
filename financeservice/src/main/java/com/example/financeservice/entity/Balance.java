package com.example.financeservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "balances")
@Data
public class Balance {

    @Id
    private Integer balance_id;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Column(name = "user_id")
    private AppUser appUserId;

    private BigDecimal amount;
    private LocalDateTime last_updated;
}