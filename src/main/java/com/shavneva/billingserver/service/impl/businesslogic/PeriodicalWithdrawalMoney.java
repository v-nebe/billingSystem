package com.shavneva.billingserver.service.impl.businesslogic;

import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.InsufficientFundsException;
import com.shavneva.billingserver.exception.MoneyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PeriodicalWithdrawalMoney {
    @Autowired
    private BillingService billingService;

    @Scheduled(cron = "${my.cron.expression:0 0 0 L * *}")
    public void performPeriodicalWithdrawal() {

        List<User> userList = getAllUsers();

        for (User user : userList) {
            BigDecimal tariffCost = getTariffCostForUser(user);
            try {
                billingService.billForServices(user, tariffCost);
            } catch (MoneyNotFoundException | InsufficientFundsException e) {

            }
        }
    }

    // Метод для получения списка всех пользователей
    private List<User> getAllUsers() {

    }

    // Метод для получения стоимости тарифа для каждого пользователя
    private BigDecimal getTariffCostForUser(User user) {

    }
}
