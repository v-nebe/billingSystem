package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.controller.ICrudController;
import com.shavneva.billingserver.converter.impl.SubscriberMapper;
import com.shavneva.billingserver.dto.SubscriberDto;
import com.shavneva.billingserver.entities.Subscriber;
import com.shavneva.billingserver.service.impl.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subscriber")
public class SubscriberController implements ICrudController<SubscriberDto> {
    private final SubscriberService subscriberService;
    private final SubscriberMapper subscriberMapper;

    @Autowired
    public SubscriberController(SubscriberService subscriberService, SubscriberMapper subscriberMapper) {
        this.subscriberService = subscriberService;
        this.subscriberMapper = subscriberMapper;
    }

    public SubscriberDto create(SubscriberDto subscriberDto) {
        Subscriber newSubscriber = subscriberMapper.mapToEntity(subscriberDto);
        Subscriber createdSubscriber = subscriberService.create(newSubscriber);
        return subscriberMapper.mapToDto(createdSubscriber);
    }

    public List<SubscriberDto> getAll() {
        return subscriberMapper.mapAll(subscriberService.getAll());
    }

    public SubscriberDto getById(int id) {
        return subscriberMapper.mapToDto(
                subscriberService.getById(id)
        );
    }

    public SubscriberDto update(SubscriberDto newDTO) {
        Subscriber updatedSubscriber = subscriberMapper.mapToEntity(newDTO);
        subscriberService.update(updatedSubscriber);
        return subscriberMapper.mapToDto(updatedSubscriber);
    }

    public String delete(int id) {
        return subscriberService.delete(id);
    }
}
