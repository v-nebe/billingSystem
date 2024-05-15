package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.dto.NotificationSettingsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Value("${application.notification.choose}")
    private boolean notificationChoose;

    @PostMapping("/notifications/settings")
    public void saveNotificationSettings(@RequestBody NotificationSettingsDto settingsDto) {
        // Ваша логика для сохранения настроек уведомлений
        // Можно обновить значение в application.yaml, но в этом примере оно будет сохранено только в памяти приложения
        this.notificationChoose = settingsDto.isChoose();
    }
}