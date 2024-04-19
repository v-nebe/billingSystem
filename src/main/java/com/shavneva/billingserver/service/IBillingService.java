package com.shavneva.billingserver.service;

import com.shavneva.billingserver.entities.User;

import java.math.BigDecimal;

public interface IBillingService<E> {
    void billForServices(E e, BigDecimal amount);

    void depositMoney(E e, BigDecimal amount);
}
