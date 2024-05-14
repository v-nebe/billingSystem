package com.shavneva.billingserver.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {

    private Integer accountId;

    @NotNull(message = "Сумма должна быть указана")
    @PositiveOrZero(message = "Сумма должна быть положительной или нулевой")
    private BigDecimal amount;
}
