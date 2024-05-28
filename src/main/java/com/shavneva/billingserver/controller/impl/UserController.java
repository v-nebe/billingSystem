package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.converter.impl.*;
import com.shavneva.billingserver.dto.*;
import com.shavneva.billingserver.entities.*;
import com.shavneva.billingserver.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User, UserDto> {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService, UserMapper userMapper, UserService userService1) {
        super(userService, userMapper);
        this.userService = userService1;
    }
    @Override
    public UserDto create(UserDto userDto) {
        return super.create(userDto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.email == authentication.name")
    public List<UserDto> getAll() {
        return super.getAll();
    }

    @Override
    @PreAuthorize("principal.userId == #id or hasRole('ROLE_ADMIN')")
    public UserDto getById(int id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("#newDTO.email == authentication.principal.email or hasRole('ROLE_ADMIN')")
    public UserDto update(UserDto newDTO) {
        return super.update(newDTO);
    }

    @Override
    @PreAuthorize("principal.userId == #id or hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        super.delete(id);
    }

}