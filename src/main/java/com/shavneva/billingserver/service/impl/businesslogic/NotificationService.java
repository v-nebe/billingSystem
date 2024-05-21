package com.shavneva.billingserver.service.impl.businesslogic;

import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Autowired
    public NotificationService(IEmailServiceProvider iEmailServiceProvider, ISmsServiceProvider iSmsServiceProvider, UserRepository userRepository) {
        this.iEmailServiceProvider = iEmailServiceProvider;
        this.iSmsServiceProvider = iSmsServiceProvider;
        this.userRepository = userRepository;
    }

    @Override
    public void notifyUserAboutBalance(String userEmail, BigDecimal balance, String phoneNumber) {
        User user = userRepository.findByEmail(userEmail);
        String subject = "Уведомление о балансе";
        String message = "На вашем счете осталось: " + balance.toString();
        if (user != null) {
            if ("email".equals(user.getNotificationType())) {
                iEmailServiceProvider.sendNotification(userEmail, subject, message);
            } else if ("sms".equals(user.getNotificationType())) {
                iSmsServiceProvider.sendSms(phoneNumber, message);
            }
        }
    }

    @Override
    public void notifyUserAboutDeposit(String userEmail, BigDecimal amount, String phoneNumber) {
        User user = userRepository.findByEmail(userEmail);
        String subject = "Уведомление о пополнении счета";
        String message = "Ваш счет был пополнен на сумму: " + amount.toString();
        if (user != null) {
            if ("email".equals(user.getNotificationType())) {
                iEmailServiceProvider.sendNotification(userEmail, subject, message);
            } else if ("sms".equals(user.getNotificationType())) {
                iSmsServiceProvider.sendSms(phoneNumber, message);
            }
        }
    }

    @Override
    public void notifyUserInsufficientFunds(String userEmail, String phoneNumber) {
        User user = userRepository.findByEmail(userEmail);
        String subject = "Уведомление о недостатке средств";
        String message = "На вашем счете недостаточно средств для выполнения операции";
        if (user != null) {
            if ("email".equals(user.getNotificationType())) {
                iEmailServiceProvider.sendNotification(userEmail, subject, message);
            } else if ("sms".equals(user.getNotificationType())) {
                iSmsServiceProvider.sendSms(phoneNumber, message);
            }
        }
    }
}
