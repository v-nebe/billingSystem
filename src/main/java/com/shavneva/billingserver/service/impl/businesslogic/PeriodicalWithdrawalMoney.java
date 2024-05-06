package com.shavneva.billingserver.service.impl.businesslogic;

import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.InsufficientFundsException;
import com.shavneva.billingserver.exception.MoneyNotFoundException;
import com.shavneva.billingserver.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PeriodicalWithdrawalMoney {
    @Autowired
    private BillingService billingService;
    @Autowired
    private UserService userService;

    @Scheduled(cron = "${my.cron.expression:0 0 0 L * *}")
    public void periodicalWithdrawingMoney() {

        List<User> userList = userService.getAll();

        for (User user : userList) {
            BigDecimal tariffCost = getTariffCostForUser(user);
            try {
                billingService.billForServices(user, tariffCost);
            } catch (MoneyNotFoundException | InsufficientFundsException e) {

            }
        }
    }

    private BigDecimal getTariffCostForUser(User user) {

    }
}
