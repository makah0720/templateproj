package com.example.template.service;

import com.example.template.domain.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction save(Transaction transaction);
    Optional<Transaction> findById(Long id);
    List<Transaction> findAll();
    void deleteById(Long id);
}
