package com.shavneva.billingserver.controller;

import org.springframework.http.HttpStatus;
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
    DTO getById(@PathVariable int id);

    //update
    @PutMapping("/update/{id}")
    DTO update(@PathVariable("id") int id, @RequestBody DTO newDTO);

    //delete
    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable int id);
}
