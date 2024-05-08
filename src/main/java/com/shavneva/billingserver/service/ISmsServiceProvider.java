package com.shavneva.billingserver.service;

public interface ISmsServiceProvider {
    void sendSms(String phoneNumber, String message);
}
