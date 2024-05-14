package com.shavneva.billingserver.controller;

import com.shavneva.billingserver.externals.DepositRequest;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

public interface IBillingController {
    @PostMapping("/bill/{userId}")
    void billForServices(@PathVariable Integer id, @RequestParam BigDecimal amount);

    @PostMapping("/deposit")
    void depositMoney(@RequestBody DepositRequest request);
}
