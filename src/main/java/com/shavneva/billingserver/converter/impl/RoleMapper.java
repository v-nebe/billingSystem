package com.shavneva.billingserver.converter.impl;

import com.shavneva.billingserver.converter.IMapper;
import com.shavneva.billingserver.dto.RoleDto;
import com.shavneva.billingserver.entities.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper implements IMapper<Role, RoleDto> {
    @Override
    public RoleDto mapToDto(Role entity) {
        RoleDto dto = new RoleDto();
        dto.setRoleId(entity.getRoleId());
        dto.setRoleName(entity.getRoleName());
        return dto;
    }

    @Override
    public Role mapToEntity(RoleDto roleDto) {
        Role entity = new Role();
        entity.setRoleId(roleDto.getRoleId());
        entity.setRoleName(roleDto.getRoleName());
        return entity;
    }
}