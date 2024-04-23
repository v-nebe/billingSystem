package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.service.SmsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsNotificationService {

    private final SmsProvider smsProvider;

    @Autowired
    public SmsNotificationService(SmsProvider smsProvider) {
        this.smsProvider = smsProvider;
    }

    public void sendNotification(String phoneNumber, String message) {
        smsProvider.sendSms(phoneNumber, message);
    }
}