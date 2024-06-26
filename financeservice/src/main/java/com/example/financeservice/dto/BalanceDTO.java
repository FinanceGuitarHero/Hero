package com.example.financeservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BalanceDTO {

    private Integer userId;
    private Integer transactionType;
    private BigDecimal value;
    private Integer bankId;
    private LocalDateTime time;

}