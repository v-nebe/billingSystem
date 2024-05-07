package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.controller.IBillingController;
import com.shavneva.billingserver.converter.IMapper;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.ResourceNotFoundException;
import com.shavneva.billingserver.repository.UserRepository;
import com.shavneva.billingserver.service.IBillingService;
import com.shavneva.billingserver.service.ICrudService;
import com.shavneva.billingserver.service.impl.businesslogic.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/billing")
public class BillingController implements IBillingController {
    private final IBillingService<User> IbillingService;
    private final UserRepository userRepository;
    @Autowired
    public BillingController( IBillingService<User> IbillingService, UserRepository userRepository ) {
        this.IbillingService = IbillingService;
        this.userRepository = userRepository;
    }
    @Override
    public void billForServices(Integer userId, BigDecimal amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        IbillingService.billForServices(user, amount);
    }

    @Override
    public void depositMoney(Integer userId, BigDecimal amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        IbillingService.depositMoney(user, amount);
    }
}