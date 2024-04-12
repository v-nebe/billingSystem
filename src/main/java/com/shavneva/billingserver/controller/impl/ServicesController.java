package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.controller.BaseController;
import com.shavneva.billingserver.converter.impl.ServicesMapper;
import com.shavneva.billingserver.dto.ServiceDto;
import com.shavneva.billingserver.entities.Services;
import com.shavneva.billingserver.service.ICrudService;
import com.shavneva.billingserver.service.impl.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicesController extends BaseController<Services, ServiceDto> {

    @Autowired
    public ServicesController(ServicesService servicesService, ServicesMapper servicesMapper) {
        super((ICrudService<Services>) servicesService, servicesMapper);
    }
    @Override
    //@PreAuthorize("permitAll()")
    public ServiceDto create(ServiceDto serviceDto) {
        return super.create(serviceDto);
    }

    @Override
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    //@PostFilter("hasRole('ROLE_ADMIN') or filterObject.email == authentication.name")
    public List<ServiceDto> getAll() {
        return super.getAll();
    }

    @Override
    //@PreAuthorize("principal.userId == #id or hasRole('ROLE_ADMIN')")
    public ServiceDto getById(int id) {
        return super.getById(id);
    }

    @Override
    //@PreAuthorize("#newDTO.email == authentication.principal.email or hasRole('ROLE_ADMIN')")
    public ServiceDto update(ServiceDto newDTO) {
        return super.update(newDTO);
    }

    @Override
    //@PreAuthorize("principal.userId == #id or hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        super.delete(id);
    }
}
