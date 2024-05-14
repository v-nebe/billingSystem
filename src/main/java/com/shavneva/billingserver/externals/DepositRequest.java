package com.shavneva.billingserver.externals;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DepositRequest {
    private String email;
    private BigDecimal amount;

}