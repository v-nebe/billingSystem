package com.shavneva.billingserver.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICrudController<DTO> {
    //create
    @PreAuthorize("permitAll()")
    @PostMapping("/create")
    DTO create(@Valid @RequestBody DTO dto);

    //read
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.email == authentication.name")
    @GetMapping("/getAll")
    List<DTO> getAll();
    @PreAuthorize("principal.userId == #id or hasRole('ROLE_ADMIN')")
    @GetMapping("/get/{id}")
    DTO getById(@Valid @PathVariable int id);

    //update
    @PreAuthorize("#newDTO.email == authentication.principal.email or hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    DTO update(@Valid @RequestBody DTO newDTO);

    //delete
    @PreAuthorize("principal.userId == #id or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    void delete(@Valid @PathVariable int id);
}
