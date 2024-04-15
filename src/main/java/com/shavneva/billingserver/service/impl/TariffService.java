package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.Tariff;
import com.shavneva.billingserver.repository.TariffRepository;
import org.springframework.stereotype.Service;

@Service
public class TariffService extends BaseService<Tariff, Integer> {
    public TariffService(TariffRepository repository) {
        super(repository);
    }
}
