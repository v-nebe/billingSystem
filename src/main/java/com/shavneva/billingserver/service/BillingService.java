package com.shavneva.billingserver.service;

import com.shavneva.billingserver.entities.Money;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.repository.InsufficientFundsException;
import com.shavneva.billingserver.repository.MoneyNotFoundException;
import com.shavneva.billingserver.repository.MoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingService {

    @Autowired
    private MoneyRepository moneyRepository;

    public void billForServices(User user, double amount) {
        Money money = user.getMoney();
        if (money == null) {
            throw new MoneyNotFoundException("Money account not found for user: " + user.getUserId());
        }

        double currentAmount = money.getAmount();
        if (currentAmount < amount) {
            throw new InsufficientFundsException("Insufficient funds to pay for services");
        }

        money.setAmount(currentAmount - amount);
        moneyRepository.save(money);
    }

    public void depositMoney(User user, double amount) {
        Money money = user.getMoney();
        if (money == null) {
            money = new Money();
            money.setUser(user);
        }

        money.setAmount(money.getAmount() + amount);
        moneyRepository.save(money);
    }
}