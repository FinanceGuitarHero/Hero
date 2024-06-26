package com.example.financeservice.service;

import com.example.financeservice.repository.*;
import com.example.financeservice.entity.*;
import com.example.financeservice.service.*;
import com.example.financeservice.dto.*;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public Transaction validation(TransactionDTO transactionDTO){
        Optional<Type> type = typeRepository.findById(transactionDTO.getTypeId());
        Optional<Bank> bank = bankRepository.findById(transactionDTO.getBankId());
        Optional<Category> category = categoryRepository.findById(transactionDTO.getCategoryId());
        Optional<Status> status = statusRepository.findById(transactionDTO.getStatusId());
        if (type.isEmpty() || bank.isEmpty() || category.isEmpty() || status.isEmpty()) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setTransactionTime(LocalDateTime.now());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setType(type.get());
        transaction.setBank(bank.get());
        transaction.setCategory(category.get());
        transaction.setStatus(status.get());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setUserId(transactionDTO.getUserId());
        return transaction;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    @Transactional
    public Transaction createTransaction(Transaction transaction){

        transactionRepository.save(transaction);

        return transaction;
    }

    @Transactional
    public Optional<Transaction> updateTransaction(Long transactionId, Transaction transaction) {
        Optional<Type> type = typeRepository.findById(transaction.getType().getId());
        Optional<Category> category = categoryRepository.findById(transaction.getCategory().getId());
        Optional<Status> status = statusRepository.findById(transaction.getStatus().getId());
        Optional<Bank> bank = bankRepository.findById(transaction.getBank().getId());

        if (type.isEmpty() || category.isEmpty() || status.isEmpty() || bank.isEmpty()) {
            throw new IllegalArgumentException("One or more fields are invalid");
        }

        Optional<Transaction> updatedTransaction = transactionRepository.findById(transactionId);
        if (updatedTransaction.isEmpty()) {
            throw new IllegalArgumentException("Transaction not found");
        }
        updatedTransaction.get().setType(type.get());
        updatedTransaction.get().setBank(bank.get());
        updatedTransaction.get().setCategory(category.get());
        updatedTransaction.get().setStatus(status.get());
        updatedTransaction.get().setUserId(transaction.getUserId());
        updatedTransaction.get().setTransactionTime(LocalDateTime.now());
        updatedTransaction.get().setAmount(transaction.getAmount());
        updatedTransaction.get().setDescription(transaction.getDescription());
        return updatedTransaction;
    }

    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }


    public List<Transaction> getTransactionsByUserId(Integer userId) {
        return transactionRepository.findByUserId(userId);
    }

    public List<Transaction> getTransactionsByTypeId(Integer typeId) {
        return transactionRepository.findByTypeId(typeId);
    }

    public List<Transaction> getTransactionsByCategoryId(Integer categoryId) {
        return transactionRepository.findByCategoryId(categoryId);
    }

    public List<Transaction> getTransactionsByStatusId(Integer statusId) {
        return transactionRepository.findByStatusId(statusId);
    }

    public List<Transaction> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findByTransactionTimeBetween(startDate, endDate);
    }

    public List<Transaction> getTransactionsByAmountGreaterThan(BigDecimal amount) {
        return transactionRepository.findByAmountGreaterThan(amount);
    }

    public List<Transaction> getTransactionsByAmountLessThan(BigDecimal amount) {
        return transactionRepository.findByAmountLessThan(amount);
    }

    public List<Transaction> getTransactionsByUserIdAndDateRange(Integer userId, LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findByUserIdAndTransactionTimeBetween(userId, startDate, endDate);
    }

    public List<Transaction> getFilteredTransactions(Integer userId, Integer typeId, Integer categoryId, Integer statusId,
                                                     LocalDateTime startDate, LocalDateTime endDate, BigDecimal minAmount, BigDecimal maxAmount) {
        return transactionRepository.findByUserIdAndTypeIdAndCategoryIdAndStatusIdAndTransactionTimeBetweenAndAmountGreaterThanEqualAndAmountLessThanEqual(
                userId, typeId, categoryId, statusId, startDate, endDate, minAmount, maxAmount);
    }
}