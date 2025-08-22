package com.example.template.controller;

import com.example.template.domain.Transaction;
import com.example.template.service.CategoryService;
import com.example.template.service.SupplierService;
import com.example.template.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final CategoryService categoryService;
    private final SupplierService supplierService;

    public TransactionController(TransactionService transactionService, CategoryService categoryService, SupplierService supplierService) {
        this.transactionService = transactionService;
        this.categoryService = categoryService;
        this.supplierService = supplierService;
    }

    @GetMapping
    public String listTransactions(Model model) {
        model.addAttribute("transactions", transactionService.findAll());
        return "transactions/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        return "transactions/add";
    }

    @PostMapping("/add")
    public String addTransaction(Transaction transaction) {
        transactionService.save(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Transaction transaction = transactionService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid transaction Id:" + id));
        model.addAttribute("transaction", transaction);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        return "transactions/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateTransaction(@PathVariable("id") Long id, Transaction transaction) {
        transaction.setId(id);
        transactionService.save(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable("id") Long id) {
        transactionService.deleteById(id);
        return "redirect:/transactions";
    }
}
