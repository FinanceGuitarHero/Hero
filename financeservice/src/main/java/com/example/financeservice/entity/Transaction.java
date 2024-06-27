package com.example.financeservice.entity;

import com.example.financeservice.service.BankService;
import com.example.financeservice.service.CategoryService;
import com.example.financeservice.service.UserService;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Autowired
    private BankService bankService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private boolean type;

    @ManyToOne
    @JoinColumn(name = "tg_id")
    private AppUser appUser;

    private String amount;
    private LocalDateTime transactionTime;
    private String description;

    public Transaction(String bank, String category,
                       String tg_id, boolean type, String amount,
                       LocalDateTime transactionTime, String description) {
        {
            this.bank = bankService.getBankByName(bank);
            this.category = categoryService.getCategoryByName(category);
            this.type = type;
            this.appUser = userService.getUserById(tg_id);
            this.amount = amount;
            this.transactionTime = transactionTime;
            this.description = description;
        }
    }
}
