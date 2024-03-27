package com.shavneva.billingserver.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICrudController<DTO> {
    //create
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/create")
    DTO create(@Valid @RequestBody DTO dto);

    //read
    //@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/getAll")
    List<DTO> getAll();
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    DTO getById(@Valid @PathVariable int id);

    //update
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PutMapping("/update")
    DTO update(@Valid @RequestBody DTO newDTO);

    //delete
    @PreAuthorize("#id == authentication.principal.id or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    void delete(@Valid @PathVariable int id);
}
