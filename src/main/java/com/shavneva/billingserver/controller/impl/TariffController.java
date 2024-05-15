package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.converter.impl.ServicesMapper;
import com.shavneva.billingserver.converter.impl.TariffMapper;
import com.shavneva.billingserver.dto.ServiceDto;
import com.shavneva.billingserver.dto.TariffDto;
import com.shavneva.billingserver.entities.Services;
import com.shavneva.billingserver.entities.Tariff;
import com.shavneva.billingserver.service.impl.ServicesService;
import com.shavneva.billingserver.service.impl.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tariffs")
public class TariffController extends BaseController<Tariff, TariffDto> {

    private final TariffService tariffService;
    private final ServicesService serviceService;
    @Autowired
    public TariffController(TariffService tariffService, TariffMapper tariffMapper, TariffService tariffService1, ServicesService serviceService) {
        super(tariffService, tariffMapper);
        this.tariffService = tariffService1;
        this.serviceService = serviceService;
    }

    @GetMapping("/tariffs-with-services")
    public ResponseEntity<List<TariffDto>> getAllTariffsWithServices() {
        List<Tariff> tariffs = tariffService.getAll();
        List<TariffDto> tariffDtos = new ArrayList<>();

        for (Tariff tariff : tariffs) {
            List<Services> services = serviceService.findByTariffId(tariff.getId());

            TariffMapper tariffMapper = new TariffMapper();
            TariffDto tariffDto = tariffMapper.mapToDto(tariff);

            List<ServiceDto> serviceDtos = new ArrayList<>();
            for (Services service : services) {
                ServicesMapper servicesMapper = new ServicesMapper();
                ServiceDto serviceDto = servicesMapper.mapToDto(service);
                serviceDtos.add(serviceDto);
            }
            tariffDto.setServices(serviceDtos);

            tariffDtos.add(tariffDto);
        }

        return ResponseEntity.ok(tariffDtos);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public TariffDto create(TariffDto tariffDto) {
        return super.create(tariffDto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    @PostFilter("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<TariffDto> getAll() {
        return super.getAll();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public TariffDto getById(int id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public TariffDto update(TariffDto newDto) {
        return super.update(newDto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        super.delete(id);
    }

}
