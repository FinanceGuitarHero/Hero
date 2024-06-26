package com.example.financeservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "currencies")
@Data
public class Currency {

    @Id
    @Column(name = "currency_code")
    private String code;

    @Column(name = "currency_name")
    private String currencyName;
    private String symbol;

}