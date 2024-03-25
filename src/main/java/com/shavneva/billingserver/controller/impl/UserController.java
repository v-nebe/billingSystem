package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.controller.ICrudController;
import com.shavneva.billingserver.dto.UserDto;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.service.impl.UserService;
import com.shavneva.billingserver.converter.impl.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserDto create(UserDto userDto) {
        User newUser = userMapper.mapToEntity(userDto);
        User createdUser = userService.create(newUser);
        return userMapper.mapToDto(createdUser);
    }

    //read
    public List<UserDto> getAll() {
        return userMapper.mapAll(userService.getAll());
    }
    public UserDto getById(int id) {
        return userMapper.mapToDto(
            userService.getById(id)
        );
    }

    //update
    public UserDto update(UserDto newDTO) {
        User updatedUser = userMapper.mapToEntity(newDTO);
        userService.update(updatedUser);
        return userMapper.mapToDto(updatedUser);
    }

    //delete
    public void delete(int id) {
        userService.delete(id);
    }

}