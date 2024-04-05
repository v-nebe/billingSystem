package com.shavneva.billingserver.converter.impl;

import com.shavneva.billingserver.converter.IMapper;
import com.shavneva.billingserver.dto.SubscriberDto;
import com.shavneva.billingserver.entities.Subscriber;
import org.springframework.stereotype.Service;

@Service
public class SubscriberMapper implements IMapper<Subscriber, SubscriberDto> {
    @Override
    public SubscriberDto mapToDto(Subscriber entity) {
        SubscriberDto dto = new SubscriberDto();
        dto.setSubscriberId(entity.getSubscriberId());
        dto.setAmount(entity.getAmount());
        return dto;
    }

    @Override
    public Subscriber mapToEntity(SubscriberDto subscriberDto) {
        Subscriber entity = new Subscriber();
        entity.setSubscriberId(subscriberDto.getSubscriberId());
        entity.setAmount(subscriberDto.getAmount());
        return entity;
    }
}
