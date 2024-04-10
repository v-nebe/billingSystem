package com.shavneva.billingserver.service;

import com.shavneva.billingserver.entities.Account;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.InsufficientFundsException;
import com.shavneva.billingserver.exception.MoneyNotFoundException;
import com.shavneva.billingserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingService {

    @Autowired
    private AccountRepository accountRepository;

    public void billForServices(User user, double amount) {
        Account account = user.getAccount();
        if (account == null) {
            throw new MoneyNotFoundException("Account account not found for user: " + user.getUserId());
        }

        double currentAmount = account.getAmount();
        if (currentAmount < amount) {
            throw new InsufficientFundsException("Insufficient funds to pay for services");
        }

        account.setAmount(currentAmount - amount);
        accountRepository.save(account);
    }

    public void depositMoney(User user, double amount) {
        Account account = user.getAccount();
        if (account == null) {
            account = new Account();
            account.setUser(user);
        }

        account.setAmount(account.getAmount() + amount);
        accountRepository.save(account);
    }
}