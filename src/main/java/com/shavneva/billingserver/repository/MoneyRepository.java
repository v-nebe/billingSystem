package com.shavneva.billingserver.repository;

import com.shavneva.billingserver.entities.Money;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyRepository extends JpaRepository<Money, Integer> {
}
