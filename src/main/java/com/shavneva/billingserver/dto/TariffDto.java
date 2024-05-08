package com.shavneva.billingserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TariffDto {
    Integer tariffId;
    @NotBlank
    String tariffName;
    @NotBlank
    BigDecimal price;

}
