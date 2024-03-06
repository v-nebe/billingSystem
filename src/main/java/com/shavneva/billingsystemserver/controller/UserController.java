package com.shavneva.billingsystemserver.controller;

import com.shavneva.billingsystemserver.entities.LogInRequest;
import com.shavneva.billingsystemserver.entities.LogInResponse;
import com.shavneva.billingsystemserver.entities.User;

import com.shavneva.billingsystemserver.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController  {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //create
    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    //read
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //update
    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser) {
        return userService.updateUser(id, newUser);
    }

    //delete
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    //login
    @PostMapping("/login")
    public LogInResponse logIn(@RequestBody LogInRequest logInResponse, HttpServletResponse response){
        return userService.logIn(logInResponse.getLogin(),logInResponse.getPassword(), response);
    }
}