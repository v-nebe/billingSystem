package com.shavneva.billingserver.service;

public interface IEmailServiceProvider {
    void sendNotification(String to, String subject, String text);
}
