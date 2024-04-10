package com.shavneva.billingserver.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICrudController<DTO> {
    //create
    @PostMapping("/create")
    DTO create(@Valid @RequestBody DTO dto);

    //read
    @GetMapping("/getAll")
    List<DTO> getAll();

    @GetMapping("/get/{id}")
    DTO getById(@Valid @PathVariable int id);

    //update
    @PutMapping("/update")
    DTO update(@Valid @RequestBody DTO newDTO, @Valid @PathVariable int id);

    //delete
    @DeleteMapping("/delete/{id}")
    void delete(@Valid @PathVariable int id);
}
