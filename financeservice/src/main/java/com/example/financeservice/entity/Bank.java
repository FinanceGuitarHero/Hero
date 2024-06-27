package com.example.financeservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "banks")
@Data
@NoArgsConstructor
public class Bank {

    @Id
    @Column(name = "bank_id")
    private Integer id;

    private String bankName;
    private String bankCode;

    public Bank(String bankName) {
        this.bankName = bankName;
    }
}