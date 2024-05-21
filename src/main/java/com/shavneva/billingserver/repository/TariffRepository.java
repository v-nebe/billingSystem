package com.shavneva.billingserver.repository;

import com.shavneva.billingserver.entities.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Integer> {
    List<Tariff> findByUserId(Long userId);
}