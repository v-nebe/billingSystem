package com.shavneva.billingserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface INotificationController {
    @GetMapping("/get_type")
    String getNotificationType(@RequestParam String userEmail);

    // Метод для установки нового типа уведомлений
    @PostMapping("/save_type")
    void setNotificationType(@RequestParam String userEmail, @RequestBody String newNotificationType);
}
