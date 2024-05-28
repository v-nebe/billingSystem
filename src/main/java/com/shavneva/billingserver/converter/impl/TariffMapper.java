package com.shavneva.billingserver.converter.impl;

import com.shavneva.billingserver.converter.IMapper;
import com.shavneva.billingserver.dto.TariffDto;
import com.shavneva.billingserver.entities.Tariff;
import org.springframework.stereotype.Service;

@Service
public class TariffMapper implements IMapper<Tariff, TariffDto> {

    @Override
    public TariffDto mapToDto(Tariff entity) {
        if (entity == null) {
            return null;
        }
        TariffDto dto = new TariffDto();
        dto.setTariffId(entity.getTariffId());
        dto.setTariffName(entity.getTariffName());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    @Override
    public Tariff mapToEntity(TariffDto tariffDto) {
        if (tariffDto == null) {
            return null;
        }
        Tariff entity = new Tariff();
        entity.setTariffId(tariffDto.getTariffId());
        entity.setTariffName(tariffDto.getTariffName());
        entity.setPrice(tariffDto.getPrice());
        return entity;
    }
}
