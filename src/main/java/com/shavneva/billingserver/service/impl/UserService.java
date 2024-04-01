package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.ResourceNotFoundException;
import com.shavneva.billingserver.repository.UserRepository;
import com.shavneva.billingserver.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements ICrudService<User>{
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
            return userRepository.findAll();
    }
    @Override
    public User getById(int id) {
            return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " Not Found"));
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User newUser) {
        User existingUser = userRepository.findById(newUser.getUserId()).orElseThrow(()->
                new ResourceNotFoundException("User not found. IDs don't match"));
        existingUser.setEmail(newUser.getEmail());
        existingUser.setFirstName(newUser.getFirstName());
        existingUser.setLastName(newUser.getLastName());
        existingUser.setNumber(newUser.getNumber());
        existingUser.setPassword(newUser.getPassword());
        return userRepository.save(existingUser);

    }

    @Override
    public String delete(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "User has been deleted ";
    }

}