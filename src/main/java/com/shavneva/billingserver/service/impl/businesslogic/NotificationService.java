package com.shavneva.billingserver.service.impl.businesslogic;

import com.shavneva.billingserver.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class NotificationService implements INotificationService {

    private final EmailServiceProvider emailServiceProvider;
    private final SmsServiceProvider smsServiceProvider;

    @Autowired
    public NotificationService(EmailServiceProvider emailServiceProvider, SmsServiceProvider smsServiceProvider) {
        this.emailServiceProvider = emailServiceProvider;
        this.smsServiceProvider = smsServiceProvider;
    }

    @Override
    public void notifyUserAboutBalance(String userEmail, BigDecimal balance, String phoneNumber) {
        boolean choose = false;
        String subject = "Уведомление о балансе";
        String message = "На вашем счете осталось: " + balance.toString();
        if(choose == true){
            emailServiceProvider.sendNotification(userEmail, subject, message);
        }else {
            smsServiceProvider.sendSms(phoneNumber, message);
        }
    }

    @Override
    public void notifyUserAboutDeposit(String userEmail, BigDecimal amount, String phoneNumber) {
        boolean choose = false;
        String subject = "Уведомление о пополнении счета";
        String message = "Ваш счет был пополнен на сумму: " + amount.toString();
        if(choose == true){
            emailServiceProvider.sendNotification(userEmail, subject, message);
        }else {
            smsServiceProvider.sendSms(phoneNumber, message);
        }
    }
}
