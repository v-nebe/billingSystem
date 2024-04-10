package com.shavneva.billingserver.service;

import com.shavneva.billingserver.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class BaseService<T, ID> {

    private final JpaRepository<T, ID> repository;

    public BaseService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public T getById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity with id " + id + " not found"));
    }

    public T create(T entity) {
        return repository.save(entity);
    }

    public T update(T updatedEntity, ID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Entity with id " + id + " not found");
        }
        return repository.save(updatedEntity);
    }

    public void delete(ID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Entity with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}