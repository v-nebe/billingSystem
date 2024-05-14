package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.Tariff;
import com.shavneva.billingserver.exception.ResourceNotFoundException;
import com.shavneva.billingserver.repository.TariffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffService extends BaseService<Tariff, Integer> {
    private final TariffRepository tariffRepository;

    public TariffService(TariffRepository repository) {
        super(repository);
        this.tariffRepository = repository;
    }

    public List<Tariff> findByUserId(Integer userId) {
        return tariffRepository.findByUserId(Long.valueOf(userId));
    }

    public Tariff findById(Integer tariffId) {
        return tariffRepository.findById(tariffId)
                .orElseThrow(() -> new ResourceNotFoundException("Tariff with id " + tariffId + " not found"));
    }
}
