package com.shavneva.billingserver.service.impl.businesslogic;

import com.shavneva.billingserver.service.IEmailServiceProvider;
import com.shavneva.billingserver.service.INotificationService;
import com.shavneva.billingserver.service.ISmsServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class NotificationService implements INotificationService {

    private final IEmailServiceProvider iEmailServiceProvider;
    private final ISmsServiceProvider iSmsServiceProvider;
    // Флаг, определяющий способ уведомления: true - через электронную почту, false - через SMS
    private final boolean choose;

    @Autowired
    public NotificationService(IEmailServiceProvider iEmailServiceProvider, ISmsServiceProvider iSmsServiceProvider,
                               @Value("${application.notification.choose}") boolean choose) {
        this.iEmailServiceProvider = iEmailServiceProvider;
        this.iSmsServiceProvider = iSmsServiceProvider;
        this.choose = choose;
    }

    @Override
    public void notifyUserAboutBalance(String userEmail, BigDecimal balance, String phoneNumber) {
        String subject = "Уведомление о балансе";
        String message = "На вашем счете осталось: " + balance.toString();
        if(choose){
            iEmailServiceProvider.sendNotification(userEmail, subject, message);
        }else {
            iSmsServiceProvider.sendSms(phoneNumber, message);
        }
    }

    @Override
    public void notifyUserAboutDeposit(String userEmail, BigDecimal amount, String phoneNumber) {
        String subject = "Уведомление о пополнении счета";
        String message = "Ваш счет был пополнен на сумму: " + amount.toString();
        if(choose){
            iEmailServiceProvider.sendNotification(userEmail, subject, message);
        }else {
            iSmsServiceProvider.sendSms(phoneNumber, message);
        }
    }

    @Override
    public void notifyUserInsufficientFunds(String userEmail, String phoneNumber) {
        String subject = "Уведомление о недостатке средств";
        String message = "На вашем счете недостаточно средств для выполнения операции";
        if (choose) {
            iEmailServiceProvider.sendNotification(userEmail, subject, message);
        } else {
            iSmsServiceProvider.sendSms(phoneNumber, message);
        }
    }
}
