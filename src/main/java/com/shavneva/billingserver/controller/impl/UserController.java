package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.controller.ICrudController;
import com.shavneva.billingserver.dto.UserDto;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.service.impl.UserService;
import com.shavneva.billingserver.converter.impl.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController implements ICrudController<UserDto> {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;

    }

    //create
    @PreAuthorize("permitAll()")
    public UserDto create(UserDto userDto) {
        User newUser = userMapper.mapToEntity(userDto);
        User createdUser = userService.create(newUser);
        return userMapper.mapToDto(createdUser);
    }

    //read
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.email == authentication.name")
    public List<UserDto> getAll() {
        return userMapper.mapAll(userService.getAll());
    }
    @PreAuthorize("principal.userId == #id or hasRole('ROLE_ADMIN')")
    public UserDto getById(int id) {
        return userMapper.mapToDto(
                userService.getById(id)
        );
    }

    //update
    @PreAuthorize("#newDTO.email == authentication.principal.email or hasRole('ROLE_ADMIN')")
    public UserDto update(UserDto newDTO, int id) {
        User updatedUser = userMapper.mapToEntity(newDTO);
        userService.update(updatedUser, id);
        return userMapper.mapToDto(updatedUser);
    }

    //delete
    @PreAuthorize("principal.userId == #id or hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        userService.delete(id);
    }

}