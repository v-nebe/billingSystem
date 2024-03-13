package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.controller.ICrudController;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController implements ICrudController<User> {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //create
    public User create(@RequestBody User dto) {
        return userService.create(dto);
    }

    //read
    public List<User> getAll() {
        return userService.getAll();
    }

    public User getById(Long id) {
        return userService.getById(id);
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