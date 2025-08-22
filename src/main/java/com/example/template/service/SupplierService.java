package com.example.template.service;

import com.example.template.domain.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    Supplier save(Supplier supplier);
    Optional<Supplier> findById(Long id);
    List<Supplier> findAll();
    void deleteById(Long id);
}
