package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.Role;
import com.shavneva.billingserver.repository.RoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<Role, Integer>{
    public RoleService(RoleRepository repository) {
        super(repository);
    }
}
