package com.shavneva.billingserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TariffDto {
    Integer tariffId;

    @NotBlank
    String tariffName;

    @NotNull(message = "Сумма должна быть указана")
    @PositiveOrZero(message = "Сумма должна быть положительной или нулевой")
    BigDecimal price;

}
