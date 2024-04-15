package com.shavneva.billingserver.service.impl;


import com.shavneva.billingserver.entities.BaseEntity;
import com.shavneva.billingserver.exception.ResourceNotFoundException;
import com.shavneva.billingserver.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class BaseService<T extends BaseEntity, ID> implements ICrudService<T> {

    private final JpaRepository<T, Integer> repository;

    @Autowired
    public BaseService(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    public List<T> getAll() {
        return repository.findAll();
    }


    public T getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity with id " + id + " not found"));
    }

    public T create(T entity) {
        return repository.save(entity);
    }

    public T update(T updatedEntity) {
        int id =  updatedEntity.getId();
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Entity with id " + id + " not found");
        }
        return repository.save(updatedEntity);
    }

    public void delete(int id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Entity with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}