package com.shavneva.billingserver.dto;

import jakarta.validation.constraints.NotBlank;

public class ServiceDto {
    Integer serviceId;
    @NotBlank
    String service;

}
