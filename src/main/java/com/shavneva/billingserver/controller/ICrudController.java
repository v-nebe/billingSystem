package com.shavneva.billingserver.controller;

import jakarta.validation.Valid;
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
    DTO update(@Valid @RequestBody DTO newDTO);

    //delete
    @DeleteMapping("/delete/{id}")
    void delete(@Valid @PathVariable int id);
}
