package com.shavneva.billingserver.converter;

public interface IMapper<E, Dto> {
    //из entity в dto
    Dto mapToDto(E entity);

    //из dto в entity
    E mapToEntity(Dto dto);

}
