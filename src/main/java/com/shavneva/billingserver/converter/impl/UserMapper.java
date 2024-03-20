package com.shavneva.billingserver.converter.impl;

import com.shavneva.billingserver.converter.IMapper;
import com.shavneva.billingserver.dto.UserDto;
import com.shavneva.billingserver.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements IMapper<User, UserDto> {

    //из entity в dto
    @Override
    public UserDto mapToDto(User entity){
        UserDto dto = new UserDto ();
        dto.setUserId(entity.getUserId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setNumber(entity.getNumber());
        dto.setPassword(entity.getPassword());
        return dto;
    }

    //из dto в entity
    @Override
    public User mapToEntity(UserDto dto){
        User entity = new User();
        entity.setUserId(dto.getUserId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setNumber(dto.getNumber());
        entity.setPassword(dto.getPassword());
        return entity;
    }

}