package com.shavneva.billingserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ServiceDto {
    Integer serviceId;
    @NotBlank
    String service;

}
