package com.shavneva.billingserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TariffDto {
    Integer tariffId;
    @NotBlank
    String tariffName;
    @NotBlank
    String  price;

}
