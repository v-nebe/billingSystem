package com.shavneva.billingserver.externals;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SmsRequest {
    private String phoneNumber;
    private String message;
}