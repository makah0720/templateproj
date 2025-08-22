package com.example.template.service;

import com.example.template.domain.Saving;

import java.util.List;
import java.util.Optional;

public interface SavingsService {
    Saving save(Saving saving);
    Optional<Saving> findById(Long id);
    List<Saving> findAll();
    void deleteById(Long id);
}
