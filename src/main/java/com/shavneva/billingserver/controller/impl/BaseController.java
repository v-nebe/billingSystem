package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.controller.ICrudController;
import com.shavneva.billingserver.converter.IMapper;
import com.shavneva.billingserver.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public class BaseController <T, DTO>  implements ICrudController<DTO> {
    private final ICrudService<T> service;
    private final IMapper<T, DTO> mapper;

    @Autowired
    public BaseController(ICrudService<T> service, IMapper<T, DTO> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public DTO create(DTO dto) {
        T entity = mapper.mapToEntity(dto);
        T createdEntity = service.create(entity);
        return mapper.mapToDto(createdEntity);
    }

    @Override
    public List<DTO> getAll() {
        return mapper.mapAll(service.getAll());
    }

    @Override
    public DTO getById(int id) {
        return mapper.mapToDto(service.getById(id));
    }

    @Override
    public DTO update(DTO newDTO) {
        T entity = mapper.mapToEntity(newDTO);
        service.update(entity);
        return mapper.mapToDto(entity);
    }

    @Override
    public void delete(int id) {
        service.delete(id);
    }
}

