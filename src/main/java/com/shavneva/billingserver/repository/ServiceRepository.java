package com.shavneva.billingserver.repository;


import com.shavneva.billingserver.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Services, Integer> {
    List<Services> findByTariffId(Long tariffId);
}