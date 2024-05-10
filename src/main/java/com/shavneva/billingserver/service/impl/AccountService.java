package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends BaseService<Account, Integer>{
    public AccountService(JpaRepository<Account, Integer> repository) {
        super(repository);
    }
}
