package com.shavneva.billingserver.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IBillingController {
    @PostMapping("/bill/{userId}")
    void billForServices(@PathVariable Integer id, @RequestParam double amount);

    @PostMapping("/deposit/{userId}")
    void depositMoney(@PathVariable Integer id, @RequestParam double amount);
}
