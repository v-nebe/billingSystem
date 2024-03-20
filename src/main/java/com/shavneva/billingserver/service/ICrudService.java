package com.shavneva.billingserver.service;

import com.shavneva.billingserver.entities.User;

import java.util.List;

public interface ICrudService<E> {
    List<E> getAll();

    E getById(int id);

    E create(E user);

    E update(int id, E newEntity);

    void delete(int id);
}
