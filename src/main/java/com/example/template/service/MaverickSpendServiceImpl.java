package com.example.template.service;

import com.example.template.domain.Transaction;
import com.example.template.domain.TransactionType;
import com.example.template.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaverickSpendServiceImpl implements MaverickSpendService {

    private final TransactionRepository transactionRepository;

    public MaverickSpendServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findMaverickSpend() {
        return transactionRepository.findAll().stream()
                .filter(this::isMaverick)
                .collect(Collectors.toList());
    }

    private boolean isMaverick(Transaction transaction) {
        return transaction.getType() != TransactionType.PO || !transaction.getSupplier().isApproved();
    }
}
