package com.shavneva.billingserver.dto;

import jakarta.validation.constraints.NotBlank;

public class TariffDto {
    Integer tariffId;
    @NotBlank
    String tariffName;
    @NotBlank
    String  price;

}
