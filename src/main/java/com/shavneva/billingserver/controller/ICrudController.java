package com.shavneva.billingserver.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICrudController<DTO> {
    //create
    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    @PostMapping("/create")
    DTO create(@Valid @RequestBody DTO dto);

    //read
    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    @GetMapping("/getAll")
    List<DTO> getAll();
    @PreAuthorize("#id == authentication.principal.id or hasRole('ROLE_ADMIN')")
    @GetMapping("/get/{id}")
    DTO getById(@Valid @PathVariable int id);

    //update
    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    @PutMapping("/update")
    DTO update(@Valid @RequestBody DTO newDTO);

    //delete
    @PreAuthorize("#id == authentication.principal.id or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    void delete(@Valid @PathVariable int id);
}
