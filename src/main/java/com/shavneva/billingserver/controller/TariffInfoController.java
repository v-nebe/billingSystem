package com.shavneva.billingserver.controller;

import com.shavneva.billingserver.converter.impl.ServicesMapper;
import com.shavneva.billingserver.converter.impl.TariffMapper;
import com.shavneva.billingserver.dto.ServiceDto;
import com.shavneva.billingserver.dto.TariffDto;
import com.shavneva.billingserver.dto.TariffInfoDTO;
import com.shavneva.billingserver.entities.Services;
import com.shavneva.billingserver.entities.Tariff;
import com.shavneva.billingserver.service.impl.ServicesService;
import com.shavneva.billingserver.service.impl.TariffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tariff-info")
public class TariffInfoController {

    private final TariffService tariffService;
    private final ServicesService serviceService;
    private final TariffMapper tariffMapper;
    private final ServicesMapper serviceMapper;

    public TariffInfoController(TariffService tariffService, ServicesService serviceService,
                                TariffMapper tariffMapper, ServicesMapper serviceMapper) {
        this.tariffService = tariffService;
        this.serviceService = serviceService;
        this.tariffMapper = tariffMapper;
        this.serviceMapper = serviceMapper;
    }

    @GetMapping("/{tariffId}")
    public ResponseEntity<TariffInfoDTO> getTariffInfo(@PathVariable Integer tariffId) {
        Tariff tariff = tariffService.findById(tariffId);
        if (tariff == null) {
            return ResponseEntity.notFound().build();
        }

        List<Services> services = serviceService.findByTariffId(tariffId);

        TariffDto tariffDto = tariffMapper.mapToDto(tariff);
        List<ServiceDto> serviceDtos = serviceMapper.toDtoList(services);

        TariffInfoDTO tariffInfoDTO = new TariffInfoDTO();
        tariffInfoDTO.setTariff(tariffDto);
        tariffInfoDTO.setServices(serviceDtos);

        return ResponseEntity.ok(tariffInfoDTO);
    }
}