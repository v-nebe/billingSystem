package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.Role;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.repository.UserRepository;
import com.shavneva.billingserver.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements ICrudService<User>{
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User currentUser = userRepository.findByEmail(email);
        Collection<Role> roles = currentUser.getRoles();

        if (roles.stream().map(role->role.getRoleName()).anyMatch(x->x.equals("ADMIN"))) {
            return userRepository.findAll();
        }
        return Collections.singletonList(currentUser);
    }
    @Override
    public User getById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User newUser) {
        User existingUser = userRepository.findById(newUser.getUserId()).orElseThrow(()->
                new IllegalArgumentException("User not found. IDs don't match"));
        existingUser.setEmail(newUser.getEmail());
        existingUser.setFirstName(newUser.getFirstName());
        existingUser.setLastName(newUser.getLastName());
        existingUser.setNumber(newUser.getNumber());
        existingUser.setPassword(newUser.getPassword());
        return userRepository.save(existingUser);

    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

}