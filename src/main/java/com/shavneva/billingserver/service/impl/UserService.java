package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.ResourceNotFoundException;
import com.shavneva.billingserver.repository.UserRepository;
import com.shavneva.billingserver.service.GenericService;
import com.shavneva.billingserver.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends GenericService<User, Integer> {

    public UserService(UserRepository repository) {
        super(repository);
    }
}