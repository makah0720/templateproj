package com.example.template.service;

import com.example.template.domain.Saving;
import com.example.template.repository.SavingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavingsServiceImpl implements SavingsService {

    private final SavingRepository savingRepository;

    public SavingsServiceImpl(SavingRepository savingRepository) {
        this.savingRepository = savingRepository;
    }

    @Override
    public Saving save(Saving saving) {
        return savingRepository.save(saving);
    }

    @Override
    public Optional<Saving> findById(Long id) {
        return savingRepository.findById(id);
    }

    @Override
    public List<Saving> findAll() {
        return savingRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        savingRepository.deleteById(id);
    }
}
