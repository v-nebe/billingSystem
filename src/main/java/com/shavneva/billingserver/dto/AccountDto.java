package com.shavneva.billingserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {

    private Integer accountId;
    @NotBlank
    private BigDecimal amount;
}
