package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.Services;
import com.shavneva.billingserver.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService extends BaseService<Services, Integer> {
    private final ServiceRepository serviceRepository;

    public ServicesService(ServiceRepository repository) {
        super(repository);
        this.serviceRepository = repository;
    }

    public List<Services> findByTariffId(Integer tariffId) {
        return serviceRepository.findByTariffId(Long.valueOf(tariffId));
    }
}
