package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.controller.ICrudController;
import com.shavneva.billingserver.converter.impl.TariffMapper;
import com.shavneva.billingserver.dto.TariffDto;
import com.shavneva.billingserver.entities.Tariff;
import com.shavneva.billingserver.service.impl.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/tariffs")
public class TariffController implements ICrudController<TariffDto> {

    private final TariffService tariffService;
    private final TariffMapper tariffMapper;

    @Autowired
    public TariffController(TariffService tariffService, TariffMapper tariffMapper) {
        this.tariffService = tariffService;
        this.tariffMapper = tariffMapper;
    }

    public TariffDto create(TariffDto tariffDto) {
        Tariff newTariff = tariffMapper.mapToEntity(tariffDto);
        Tariff createdTariff = tariffService.create(newTariff);
        return tariffMapper.mapToDto(createdTariff);
    }

    public List<TariffDto> getAll() {
        return tariffMapper.mapAll(tariffService.getAll());
    }

    public TariffDto getById(int id) {
        return tariffMapper.mapToDto(
                tariffService.getById(id)
        );
    }

    public TariffDto update(TariffDto newDTO, int id) {
        Tariff updatedTariff = tariffMapper.mapToEntity(newDTO);
        tariffService.update(updatedTariff, id);
        return tariffMapper.mapToDto(updatedTariff);
    }

    public void delete(int id) {
        tariffService.delete(id);
    }
}
