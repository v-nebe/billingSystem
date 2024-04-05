package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.controller.ICrudController;
import com.shavneva.billingserver.converter.impl.ServicesMapper;
import com.shavneva.billingserver.dto.ServiceDto;
import com.shavneva.billingserver.entities.Services;
import com.shavneva.billingserver.service.impl.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicesController implements ICrudController<ServiceDto> {

    private final ServicesService servicesService;
    private final ServicesMapper servicesMapper;

    @Autowired
    public ServicesController(ServicesService servicesService, ServicesMapper servicesMapper) {
        this.servicesService = servicesService;
        this.servicesMapper = servicesMapper;
    }

    public ServiceDto create(ServiceDto serviceDto) {
        Services newService = servicesMapper.mapToEntity(serviceDto);
        Services createdService = servicesService.create(newService);
        return servicesMapper.mapToDto(createdService);
    }

    public List<ServiceDto> getAll() {
        return servicesMapper.mapAll(servicesService.getAll());
    }

    public ServiceDto getById(int id) {
        return servicesMapper.mapToDto(
                servicesService.getById(id)
        );
    }

    public ServiceDto update(ServiceDto newDTO) {
        Services updatedService = servicesMapper.mapToEntity(newDTO);
        servicesService.update(updatedService);
        return servicesMapper.mapToDto(updatedService);
    }

    public String delete(int id) {
        return servicesService.delete(id);
    }
}
