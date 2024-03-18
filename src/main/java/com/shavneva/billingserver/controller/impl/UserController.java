package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.controller.ICrudController;
import com.shavneva.billingserver.dto.UserDto;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.service.impl.UserService;
import com.shavneva.billingserver.converter.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/users")
public class UserController implements ICrudController<UserDto> {
    private final UserService userService;
    private final MappingUtils mappingUtils;

    @Autowired
    public UserController(UserService userService, MappingUtils mappingUtils) {
        this.userService = userService;
        this.mappingUtils = mappingUtils;
    }

    //create
    public UserDto create(@RequestBody User dto) {
        return userService.create(dto);
    }

    //read
    public List<UserDto> getAll() {
        return userService.getAll()
                .stream()
                .map(mappingUtils::mapToDto)
                .collect(toList());
    }

    public UserDto getById(@PathVariable Long id) {
        return mappingUtils.mapToDto(
            userService.getById(id)
        );
    }

    //update
    public User update(@PathVariable Long id, @RequestBody User newUser) {
        return userService.update(id, newUser);
    }

    //delete
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}