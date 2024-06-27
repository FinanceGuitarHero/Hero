package com.example.financeservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "t_users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String name;

    private String tg_id;

    @OneToOne
    @JoinColumn(name = "balance_id")
    private Balance balance;

    @OneToMany
    @JoinColumn(name = "bank_id")
    private List<Bank> banks;

    @OneToMany
    @JoinColumn(name = "transaction_id")
    private List<Transaction> transactions;

    public AppUser(String name, String tg_id) {
        this.name = name;
        this.tg_id = tg_id;
    }
}
