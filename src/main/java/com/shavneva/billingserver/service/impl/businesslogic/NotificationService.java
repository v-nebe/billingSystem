package com.shavneva.billingserver.service.impl.businesslogic;

import com.shavneva.billingserver.service.IEmailServiceProvider;
import com.shavneva.billingserver.service.INotificationService;
import com.shavneva.billingserver.service.ISmsServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class NotificationService implements INotificationService {

    private final IEmailServiceProvider IEmailServiceProvider;
    private final ISmsServiceProvider iSmsServiceProvider;
    // Флаг, определяющий способ уведомления: true - через электронную почту, false - через SMS
    private final boolean choose;

    @Autowired
    public NotificationService(IEmailServiceProvider IEmailServiceProvider, SmsServiceProvider iSmsServiceProvider,
                               boolean choose) {
        this.IEmailServiceProvider = IEmailServiceProvider;
        this.iSmsServiceProvider = iSmsServiceProvider;
        this.choose = choose;
    }

    @Override
    public void notifyUserAboutBalance(String userEmail, BigDecimal balance, String phoneNumber) {
        String subject = "Уведомление о балансе";
        String message = "На вашем счете осталось: " + balance.toString();
        if(choose){
            IEmailServiceProvider.sendNotification(userEmail, subject, message);
        }else {
            iSmsServiceProvider.sendSms(phoneNumber, message);
        }
    }

    @Override
    public void notifyUserAboutDeposit(String userEmail, BigDecimal amount, String phoneNumber) {
        String subject = "Уведомление о пополнении счета";
        String message = "Ваш счет был пополнен на сумму: " + amount.toString();
        if(choose){
            IEmailServiceProvider.sendNotification(userEmail, subject, message);
        }else {
            iSmsServiceProvider.sendSms(phoneNumber, message);
        }
    }
}
