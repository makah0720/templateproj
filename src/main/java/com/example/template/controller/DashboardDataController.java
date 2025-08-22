package com.example.template.controller;

import com.example.template.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardDataController {

    private final TransactionService transactionService;

    public DashboardDataController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/spend-by-category")
    public Map<String, Double> getSpendByCategory() {
        return transactionService.findAll().stream()
                .collect(Collectors.groupingBy(
                        t -> t.getCategory().getName(),
                        Collectors.summingDouble(t -> t.getAmount().doubleValue())
                ));
    }
}
