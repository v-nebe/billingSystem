package com.shavneva.billingserver.repository;

import com.shavneva.billingserver.entities.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {
}

