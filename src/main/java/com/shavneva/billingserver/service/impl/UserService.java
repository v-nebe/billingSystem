package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Integer> {
    public UserService(UserRepository repository) {
        super(repository);
    }
}