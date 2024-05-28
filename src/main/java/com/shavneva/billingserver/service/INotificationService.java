package com.shavneva.billingserver.service;

import java.math.BigDecimal;

public interface INotificationService {
    void notifyUserAboutBalance(String userEmail, BigDecimal balance, String phoneNumber, BigDecimal amount);

    void notifyUserAboutDeposit(String userEmail, BigDecimal amount, String phoneNumber);
    void notifyUserInsufficientFunds(String userEmail, String phoneNumber);
}
