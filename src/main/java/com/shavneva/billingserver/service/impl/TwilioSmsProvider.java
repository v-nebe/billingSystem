package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.service.SmsProvider;
import org.springframework.stereotype.Component;


@Component
public class TwilioSmsProvider implements SmsProvider {

    @Override
    public void sendSms(String phoneNumber, String message) {

    }
}