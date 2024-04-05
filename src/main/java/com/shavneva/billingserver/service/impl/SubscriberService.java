package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.Subscriber;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.ResourceNotFoundException;
import com.shavneva.billingserver.repository.SubscriberRepository;
import com.shavneva.billingserver.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberService implements ICrudService<Subscriber> {
    private final SubscriberRepository subscriberRepository;

    @Autowired
    public SubscriberService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public List<Subscriber> getAll() {
        return subscriberRepository.findAll();
    }

    @Override
    public Subscriber getById(int id) {
        return subscriberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " Not Found"));
    }

    @Override
    public Subscriber create(Subscriber newSubscriber) {
        return subscriberRepository.save(newSubscriber);
    }

    @Override
    public Subscriber update(Subscriber newSubscriber) {
        Subscriber existingSubscriber = subscriberRepository.findById(newSubscriber.getSubscriberId()).orElseThrow(()->
                new ResourceNotFoundException("Subscriber not found. IDs don't match"));
        existingSubscriber.setAmount(newSubscriber.getAmount());
        return subscriberRepository.save(existingSubscriber);

    }

    @Override
    public String delete(int id) {
        Subscriber subscriber = subscriberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid subscriber Id:" + id));
        subscriberRepository.delete(subscriber);
        return "Subscriber has been deleted ";
    }
}
