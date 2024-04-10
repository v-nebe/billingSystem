package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.Services;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.ResourceNotFoundException;
import com.shavneva.billingserver.repository.ServiceRepository;
import com.shavneva.billingserver.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService implements ICrudService<Services> {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServicesService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<Services> getAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Services getById(int id) {
        return serviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " Not Found"));
    }

    @Override
    public Services create(Services services) {
        return serviceRepository.save(services);
    }

    @Override
    public Services update(Services newService) {
        Services existingService = serviceRepository.findById(newService.getServiceId()).orElseThrow(()->
                new ResourceNotFoundException("User not found. IDs don't match"));
        existingService.setService(newService.getService());
        return serviceRepository.save(existingService);
    }

    @Override
    public void delete(int id) {
        Services services = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid service Id:" + id));
        serviceRepository.delete(services);
    }
}
