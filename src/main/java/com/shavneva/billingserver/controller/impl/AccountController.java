package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.converter.IMapper;
import com.shavneva.billingserver.dto.AccountDto;
import com.shavneva.billingserver.dto.RoleDto;
import com.shavneva.billingserver.dto.TariffDto;
import com.shavneva.billingserver.entities.Account;
import com.shavneva.billingserver.entities.Role;
import com.shavneva.billingserver.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController extends BaseController<Account, AccountDto>{
    @Autowired
    public AccountController(ICrudService<Account> service, IMapper<Account, AccountDto> mapper) {
        super(service, mapper);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AccountDto create(AccountDto accountDto) {
        return super.create(accountDto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostFilter("hasRole('ROLE_ADMIN')")
    public List<AccountDto> getAll() {
        return super.getAll();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AccountDto getById(int id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AccountDto update(AccountDto newDto) {
        return super.update(newDto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        super.delete(id);
    }
}
