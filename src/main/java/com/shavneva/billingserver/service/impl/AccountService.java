package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.Account;
import com.shavneva.billingserver.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountService extends BaseService<Account, Integer>{
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository repository) {
        super(repository);
        this.accountRepository = repository;
    }

    public List<Account> findByUserId(Integer userId) {
        return accountRepository.findByUserId(Long.valueOf(userId));
    }
}
