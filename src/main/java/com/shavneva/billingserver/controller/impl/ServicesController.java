package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.converter.impl.ServicesMapper;
import com.shavneva.billingserver.dto.ServiceDto;
import com.shavneva.billingserver.entities.Services;
import com.shavneva.billingserver.service.impl.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicesController extends BaseController<Services, ServiceDto> {

    @Autowired
    public ServicesController(ServicesService servicesService, ServicesMapper servicesMapper) {
        super(servicesService, servicesMapper);
    }
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ServiceDto create(ServiceDto serviceDto) {
        return super.create(serviceDto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostFilter("hasRole('ROLE_ADMIN')")
    public List<ServiceDto> getAll() {
        return super.getAll();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ServiceDto getById(int id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ServiceDto update(ServiceDto newDTO) {
        return super.update(newDTO);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        super.delete(id);
    }
}
