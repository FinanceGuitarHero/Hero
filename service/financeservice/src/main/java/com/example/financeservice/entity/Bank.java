package com.example.financeservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "banks")
public class Bank {

    @Id
    @Column(name = "bank_id")
    private Integer id;

    private String bankName;
    private String bankCode;
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer bankId) {
        this.id = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}