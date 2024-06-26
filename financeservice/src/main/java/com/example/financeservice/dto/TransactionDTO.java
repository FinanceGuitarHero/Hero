package com.example.financeservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDTO {

    private Integer userId;
    private BigDecimal amount;
    private Integer typeId;
    private Integer categoryId;
    private Integer statusId;
    private String description;
    private String currencyCode;
    private Integer bankId;
}
