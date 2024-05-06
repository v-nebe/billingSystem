package com.shavneva.billingserver.repository;

import com.shavneva.billingserver.entities.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffRepository extends JpaRepository<Tariff, Integer> {
}