package com.shavneva.billingserver;

import com.shavneva.billingserver.service.impl.businesslogic.EmailServiceProvider;
import com.shavneva.billingserver.service.impl.businesslogic.NotificationService;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

import com.shavneva.billingserver.service.impl.businesslogic.SmsServiceProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private EmailServiceProvider emailServiceProvider;
    
    @Test
    public void testNotifyUserAboutBalance_Email() {
        String userEmail = "example@example.com";
        BigDecimal balance = BigDecimal.valueOf(1000);

        // Вызываем метод, который должен отправить уведомление
        notificationService.notifyUserAboutBalance(userEmail, balance, "123456789");

        // Проверяем, что уведомление было отправлено через EmailServiceProvider
        verify(emailServiceProvider).sendNotification(eq(userEmail), anyString(), anyString());
    }
}