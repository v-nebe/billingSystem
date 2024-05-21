package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.controller.INotificationController;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.ResourceNotFoundException;
import com.shavneva.billingserver.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController implements INotificationController {

    private final UserService userService;

    @Autowired
    public NotificationController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getNotificationType(@RequestParam String userEmail) {
        User user = userService.findByEmail(userEmail);
        if (user != null) {
            return user.getNotificationType();
        } else {
            throw new ResourceNotFoundException("User not found with email: " + userEmail);
        }
    }

    @Override
    public void setNotificationType(@RequestParam String userEmail, @RequestBody String newNotificationType) {
        User user = userService.findByEmail(userEmail);
        if (user != null) {
            user.setNotificationType(newNotificationType);
            userService.save(user);
        } else {
            throw new ResourceNotFoundException("User not found with email: " + userEmail);
        }
    }
}