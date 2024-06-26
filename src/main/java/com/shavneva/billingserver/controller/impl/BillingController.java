package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.controller.IBillingController;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.ResourceNotFoundException;
import com.shavneva.billingserver.externals.DepositRequest;
import com.shavneva.billingserver.repository.UserRepository;
import com.shavneva.billingserver.service.IBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/billing")
public class BillingController implements IBillingController {
    private final IBillingService<User> iBillingService;
    private final UserRepository userRepository;

    @Autowired
    public BillingController(IBillingService<User> iBillingService, UserRepository userRepository) {
        this.iBillingService = iBillingService;
        this.userRepository = userRepository;
    }

    @Override
    public void billForServices(Integer userId, BigDecimal amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        iBillingService.billForServices(user, amount);
    }

    @Override
    public void depositMoney(@RequestBody DepositRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new ResourceNotFoundException("User not found with email: " + request.getEmail());
        }
        iBillingService.depositMoney(user.getEmail(), request.getAmount());
    }
}