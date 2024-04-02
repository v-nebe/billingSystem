package com.shavneva.billingserver.service;

import java.util.List;

public interface ICrudService<E> {
    List<E> getAll();

    E getById(int id);

    E create(E newE);

    E update(E newEntity);

    String delete(int id);
}
