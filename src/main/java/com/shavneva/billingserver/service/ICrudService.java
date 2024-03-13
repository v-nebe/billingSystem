package com.shavneva.billingserver.service;

import com.shavneva.billingserver.entities.User;

import java.util.List;

public interface ICrudService<E> {
    List<E> getAll();

    E getById(Long id);

    E create(E user);

    E update(Long id, E newEntity);

    void delete(Long id);
}
