package com.shavneva.billingserver.converter.impl;

import com.shavneva.billingserver.converter.IMapper;
import com.shavneva.billingserver.dto.AccountDto;
import com.shavneva.billingserver.entities.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper implements IMapper<Account, AccountDto> {
    @Override
    public AccountDto mapToDto(Account entity) {
        if(entity ==null){
            return null;
        }
        AccountDto dto = new AccountDto();
        dto.setAccountId(entity.getAccountId());
        dto.setAmount(entity.getAmount());
        return dto;
    }

    @Override
    public Account mapToEntity(AccountDto accountDto) {
        if(accountDto == null){
            return null;
        }
        Account entity = new Account();
        entity.setAccountId(accountDto.getAccountId());
        entity.setAmount(accountDto.getAmount());
        return entity;
    }
}
