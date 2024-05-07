package com.shavneva.billingserver.service.impl.businesslogic;

import com.shavneva.billingserver.service.IEmailServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceProvider implements IEmailServiceProvider {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailServiceProvider(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendNotification(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}