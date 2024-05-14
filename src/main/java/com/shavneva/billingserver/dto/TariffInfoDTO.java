package com.shavneva.billingserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TariffInfoDTO {
    private TariffDto tariff;
    private List<ServiceDto> services;
}
