package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.Account;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.InsufficientFundsException;
import com.shavneva.billingserver.exception.MoneyNotFoundException;
import com.shavneva.billingserver.exception.ResourceNotFoundException;
import com.shavneva.billingserver.repository.AccountRepository;
import com.shavneva.billingserver.service.IBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class BillingService implements IBillingService<User> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void billForServices(User user, BigDecimal amount) {
        Account account = user.getAccount();
        if (account == null) {
            throw new MoneyNotFoundException("Account not found for user: " + user.getUserId());
        }

        BigDecimal currentAmount = account.getAmount();
        if (currentAmount.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds to pay for services");
        }

        account.setAmount(currentAmount.subtract(amount));
        accountRepository.save(account);
    }

    @Override
    public void depositMoney(User user, BigDecimal amount) {
        Account account = user.getAccount();
        if (account == null) {
            account = new Account();
            account.setUser(user);
            account.setAmount(BigDecimal.ZERO);
        }

        account.setAmount(account.getAmount().add(amount));
        accountRepository.save(account);
    }

    @Transactional
    public void deposit(Long userId, BigDecimal amount) {
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for user: " + userId));

        BigDecimal newBalance = account.getAmount().add(amount);
        account.setAmount(newBalance);

        accountRepository.save(account);
    }
}