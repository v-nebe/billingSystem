package com.shavneva.billingserver;

import com.shavneva.billingserver.service.IEmailServiceProvider;
import com.shavneva.billingserver.service.impl.businesslogic.NotificationService;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private IEmailServiceProvider IEmailServiceProvider;
    
    @Test
    public void testNotifyUserAboutBalance_Email() {
        String userEmail = "example@example.com";
        BigDecimal balance = BigDecimal.valueOf(1000);
        BigDecimal deductedAmount = BigDecimal.valueOf(20);

        // Вызываем метод, который должен отправить уведомление
        notificationService.notifyUserAboutBalance(userEmail, balance, "123456789", deductedAmount);

        // Проверяем, что уведомление было отправлено через EmailServiceProvider
        verify(IEmailServiceProvider).sendNotification(eq(userEmail), anyString(), anyString());
    }
}