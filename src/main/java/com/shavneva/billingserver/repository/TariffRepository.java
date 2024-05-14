package com.shavneva.billingserver.repository;

import com.shavneva.billingserver.entities.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TariffRepository extends JpaRepository<Tariff, Integer> {
    List<Tariff> findByUserId(Long userId);
}