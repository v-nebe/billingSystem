package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.Tariff;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.ResourceNotFoundException;
import com.shavneva.billingserver.repository.TariffRepository;
import com.shavneva.billingserver.repository.UserRepository;
import com.shavneva.billingserver.service.GenericService;
import com.shavneva.billingserver.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffService extends GenericService<Tariff, Integer> {
    public TariffService(TariffRepository repository) {
        super(repository);
    }
}
