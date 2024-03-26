package com.shavneva.billingserver.converter;

import java.util.List;

public interface IMapper<E, Dto> {
    //из entity в dto
    Dto mapToDto(E entity);

    //из dto в entity
    E mapToEntity(Dto dto);

    List<Dto> map(List<E> e);

}
