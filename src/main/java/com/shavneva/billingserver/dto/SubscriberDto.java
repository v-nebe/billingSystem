package com.shavneva.billingserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubscriberDto {
    Integer subscriberId;
    @NotBlank
    String amount;
}
