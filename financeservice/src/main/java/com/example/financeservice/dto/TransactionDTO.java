package com.example.financeservice.dto;

import lombok.Data;

@Data
public class TransactionDTO {
    private String userId;
    private String  amount;
    private boolean type;
    private String category;
    private String description;
    private String bank;
}
