package com.example.financeservice.service;

import com.example.financeservice.dto.TransactionDTO;
import com.example.financeservice.entity.AppUser;
import com.example.financeservice.entity.Transaction;
import com.example.financeservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelegramService {
    private final UserRepository userRepository;

    private final TransactionService transactionService;

    public Optional<Object> start(String name, String tg_id) {
        AppUser appUser = new AppUser(name, tg_id);
        if (userRepository.existsByTg_id(tg_id)){
            return Optional.empty();
        }
        userRepository.save(appUser);
        return Optional.of(new Object());
    }

    public Integer addTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionService.createTransaction(new Transaction
                        (transactionDTO.getBank(), transactionDTO.getCategory(),
                        transactionDTO.getUserId(), transactionDTO.isType(),
                        transactionDTO.getAmount(), LocalDateTime.now(),
                        transactionDTO.getDescription()));
        return transaction.getId();
    }
}
