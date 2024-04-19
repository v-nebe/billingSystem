package com.shavneva.billingserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class NotificationService {

    private final EmailNotificationService emailNotificationService;
    private final SmsNotificationService smsNotificationService;

    @Autowired
    public NotificationService(EmailNotificationService emailNotificationService,
                               SmsNotificationService smsNotificationService) {
        this.emailNotificationService = emailNotificationService;
        this.smsNotificationService = smsNotificationService;
    }

    public void notifyUserAboutBalance(String userEmail, BigDecimal balance) {
        String subject = "Уведомление о балансе";
        String message = "На вашем счете осталось: " + balance.toString();
        emailNotificationService.sendNotification(userEmail, subject, message);
    }

    public void notifyUserAboutDeposit(String userEmail, BigDecimal amount) {
        String subject = "Уведомление о пополнении счета";
        String message = "Ваш счет был пополнен на сумму: " + amount.toString();
        emailNotificationService.sendNotification(userEmail, subject, message);
    }
}
