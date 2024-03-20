package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.controller.ICrudController;
import com.shavneva.billingserver.dto.UserDto;
import com.shavneva.billingserver.service.impl.UserService;
import com.shavneva.billingserver.converter.impl.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/users")
public class UserController implements ICrudController<UserDto> {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    //create
    @Override
    public UserDto create(UserDto userDto) {
        return null;
    }

    //read
    public List<UserDto> getAll() {
        return userService.getAll()
                .stream()
                .map(userMapper::mapToDto)
                .collect(toList());
    }
    public UserDto getById(@PathVariable Long id) {
        return userMapper.mapToDto(
            userService.getById(id)
        );
    }

    //update
    @Override
    public UserDto update(Long id, UserDto newDTO) {
        return null;
    }

    //delete
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}