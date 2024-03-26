package com.shavneva.billingserver.converter;

import com.shavneva.billingserver.dto.UserDto;
import com.shavneva.billingserver.entities.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface IMapper<E, Dto> {
    //из entity в dto
    Dto mapToDto(E entity);

    //из dto в entity
    E mapToEntity(Dto dto);

    default List<Dto> mapAll(List<E> e){
        return e.stream()
                .map(this::mapToDto)
                .collect(toList());
    }

}
