package com.example.financeservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "banks")
@Data
public class Bank {

    @Id
    @Column(name = "bank_id")
    private Integer id;

    private String bankName;
    private String bankCode;
    private String name;

}