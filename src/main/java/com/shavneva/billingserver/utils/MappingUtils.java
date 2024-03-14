package com.shavneva.billingserver.utils;

import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.entities.UserDto;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {
    //из entity в dto
    public UserDto mapToDto(User entity){
        UserDto dto = new UserDto();
        dto.setUserId(entity.getUserId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setNumber(entity.getNumber());
        dto.setPassword(entity.getPassword());
        return dto;
    }
    //из dto в entity
    public User mapToEntity(UserDto dto){
        User entity = new User();
        entity.setUserId(dto.getUserId());
        entity.setUserId(entity.getUserId());
        entity.setFirstName(entity.getFirstName());
        entity.setLastName(entity.getLastName());
        entity.setEmail(entity.getEmail());
        entity.setNumber(entity.getNumber());
        entity.setPassword(entity.getPassword());
        return entity;
    }
}