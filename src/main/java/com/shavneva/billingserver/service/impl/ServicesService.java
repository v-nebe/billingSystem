package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.Services;
import com.shavneva.billingserver.repository.ServiceRepository;
import com.shavneva.billingserver.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ServicesService extends BaseService<Services, Integer> {
    public ServicesService(ServiceRepository repository) {
        super(repository);
    }
}
