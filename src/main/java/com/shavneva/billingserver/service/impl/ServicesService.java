package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.Services;
import com.shavneva.billingserver.entities.Tariff;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.ResourceNotFoundException;
import com.shavneva.billingserver.repository.ServiceRepository;
import com.shavneva.billingserver.repository.TariffRepository;
import com.shavneva.billingserver.service.GenericService;
import com.shavneva.billingserver.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService extends GenericService<Services, Integer> {
    public ServicesService(ServiceRepository repository) {
        super(repository);
    }
}
