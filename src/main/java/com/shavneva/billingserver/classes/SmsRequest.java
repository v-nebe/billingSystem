package com.shavneva.billingserver.classes;

import lombok.Data;

@Data
public class SmsRequest {
    private String phoneNumber;
    private String message;

    public SmsRequest() {
    }

    public SmsRequest(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }
}