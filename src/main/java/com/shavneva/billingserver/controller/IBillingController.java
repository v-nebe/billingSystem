package com.shavneva.billingserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

public interface IBillingController {
    @PostMapping("/bill/{userId}")
    void billForServices(@PathVariable Integer id, @RequestParam BigDecimal amount);

    @PostMapping("/deposit")
    void depositMoney(@RequestParam String email, @RequestParam BigDecimal amount);
}
