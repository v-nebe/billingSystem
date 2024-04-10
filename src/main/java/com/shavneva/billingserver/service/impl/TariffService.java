package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.Tariff;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.ResourceNotFoundException;
import com.shavneva.billingserver.repository.TariffRepository;
import com.shavneva.billingserver.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffService implements ICrudService<Tariff> {
    private final TariffRepository tariffRepository;

    @Autowired
    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @Override
    public List<Tariff> getAll() {
        return tariffRepository.findAll();
    }

    @Override
    public Tariff getById(int id) {
        return tariffRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " Not Found"));
    }

    @Override
    public Tariff create(Tariff newTariff) {
        return tariffRepository.save(newTariff);
    }

    @Override
    public Tariff update(Tariff newTariff) {
        Tariff existingTariff = tariffRepository.findById(newTariff.getTariffId()).orElseThrow(()->
                new ResourceNotFoundException("Tariff not found. IDs don't match"));
        existingTariff.setTariffName(newTariff.getTariffName());
        existingTariff.setPrice(newTariff.getPrice());
        return tariffRepository.save(existingTariff);
    }

    @Override
    public void delete(int id) {
        Tariff tariff = tariffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid tariff Id:" + id));
        tariffRepository.delete(tariff);
    }
}
