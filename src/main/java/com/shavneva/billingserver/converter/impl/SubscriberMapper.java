package com.shavneva.billingserver.converter.impl;

import com.shavneva.billingserver.converter.IMapper;
import com.shavneva.billingserver.dto.SubscriberDto;
import com.shavneva.billingserver.entities.Subscriber;


public class SubscriberMapper implements IMapper<Subscriber, SubscriberDto> {
    @Override
    public SubscriberDto mapToDto(Subscriber entity) {
        return null;
    }

    @Override
    public Subscriber mapToEntity(SubscriberDto subscriberDto) {
        return null;
    }
}
