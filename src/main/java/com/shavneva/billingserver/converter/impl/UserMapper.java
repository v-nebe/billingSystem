package com.shavneva.billingserver.converter.impl;

import com.shavneva.billingserver.converter.IMapper;
import com.shavneva.billingserver.dto.*;
import com.shavneva.billingserver.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper implements IMapper<User, UserDto> {
    private final IMapper<Tariff, TariffDto> tariffMapper;
    private final IMapper<Account, AccountDto> accountMapper;
    private final IMapper<Role, RoleDto> roleMapper;

    @Autowired
    public UserMapper(IMapper<Tariff, TariffDto> tariffMapper, IMapper<Account, AccountDto> accountMapper,
                      IMapper<Role, RoleDto> roleMapper) {
        this.tariffMapper = tariffMapper;
        this.accountMapper = accountMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDto mapToDto(User entity) {
        if (entity == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setUserId(entity.getUserId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setNumber(entity.getNumber());
        dto.setPassword(entity.getPassword());
        dto.setNotificationType(entity.getNotificationType());
        dto.setTariff(tariffMapper.mapToDto(entity.getTariff()));
        dto.setAccount(accountMapper.mapToDto(entity.getAccount()));

        List<RoleDto> roleDtos = entity.getRoles() != null ? entity.getRoles().stream()
                .map(roleMapper::mapToDto)
                .collect(Collectors.toList()) : Collections.emptyList();
        dto.setRoles(roleDtos);

        return dto;
    }

    @Override
    public User mapToEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }
        User entity = new User();
        entity.setUserId(dto.getUserId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setNumber(dto.getNumber());
        entity.setPassword(dto.getPassword());
        entity.setNotificationType(dto.getNotificationType());
        entity.setTariff(tariffMapper.mapToEntity(dto.getTariff()));
        entity.setAccount(accountMapper.mapToEntity(dto.getAccount()));

        List<Role> roles = dto.getRoles() != null ? dto.getRoles().stream()
                .map(roleMapper::mapToEntity)
                .collect(Collectors.toList()) : Collections.emptyList();
        entity.setRoles(roles);

        return entity;
    }

}