package com.shavneva.billingserver.controller;

import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.ResourceNotFoundException;
import com.shavneva.billingserver.repository.UserRepository;
import com.shavneva.billingserver.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;
    private UserRepository userRepository;

    @PostMapping("/bill/{userId}")
    public void billForServices(@PathVariable Integer userId, @RequestParam double amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        billingService.billForServices(user, amount);
    }

    @PostMapping("/deposit/{userId}")
    public void depositMoney(@PathVariable Integer userId, @RequestParam double amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        billingService.depositMoney(user, amount);
    }
}