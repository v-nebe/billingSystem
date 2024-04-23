package com.shavneva.billingserver.service;

public interface SmsProvider {
    void sendSms(String phoneNumber, String message);
}