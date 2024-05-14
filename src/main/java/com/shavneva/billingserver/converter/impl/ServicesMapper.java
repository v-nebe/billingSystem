package com.shavneva.billingserver.converter.impl;

import com.shavneva.billingserver.converter.IMapper;
import com.shavneva.billingserver.dto.ServiceDto;
import com.shavneva.billingserver.entities.Services;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicesMapper implements IMapper<Services, ServiceDto> {
    @Override
    public ServiceDto mapToDto(Services entity) {
        ServiceDto dto = new ServiceDto();
        dto.setServiceId(entity.getServiceId());
        dto.setService(entity.getService());
        return dto;
    }

    @Override
    public Services mapToEntity(ServiceDto serviceDto) {
        Services entity =  new Services();
        entity.setServiceId(serviceDto.getServiceId());
        entity.setService(serviceDto.getService());
        return entity;
    }

    public List<ServiceDto> toDtoList(List<Services> services) {
        return services.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
