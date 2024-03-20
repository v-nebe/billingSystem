package com.shavneva.billingserver.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICrudController<DTO> {
    //create
    @PostMapping("/create")
    DTO create(@RequestBody DTO dto);

    //read
    @GetMapping("/getAll")
    List<DTO> getAll();
    @GetMapping("/get/{id}")
    DTO getById(@PathVariable Long id);

    //update
    @PutMapping("/update/{id}")
    DTO update(@PathVariable Long id, @RequestBody DTO newDTO);

    //delete
    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id);
}
