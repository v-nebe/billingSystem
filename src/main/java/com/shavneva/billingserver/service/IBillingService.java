package com.shavneva.billingserver.service;

import com.shavneva.billingserver.entities.User;

public interface IBillingService<E> {
    void billForServices(E e, double amount);

    void depositMoney(E e, double amount);
}
