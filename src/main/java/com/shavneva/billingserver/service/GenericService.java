package com.shavneva.billingserver.service;

import com.shavneva.billingserver.exception.ResourceNotFoundException;
import com.shavneva.billingserver.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericService<T, ID> {

    private final JpaRepository<T, ID> repository;

    public GenericService(JpaRepository<T, ID> repository) {
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