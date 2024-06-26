package com.shavneva.billingserver.service.impl.businesslogic;

import com.shavneva.billingserver.entities.Tariff;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.InsufficientFundsException;
import com.shavneva.billingserver.exception.MoneyNotFoundException;
import com.shavneva.billingserver.service.IBillingService;
import com.shavneva.billingserver.service.ICrudService;
import com.shavneva.billingserver.service.IPeriodicalWithdrawalMoney;
import com.shavneva.billingserver.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PeriodicalWithdrawalMoney implements IPeriodicalWithdrawalMoney {

    private final IBillingService<User> iBillingService;
    private final ICrudService<User> userService;
    private static final Logger logger = LoggerFactory.getLogger(Logger.class);



    @Autowired
    public PeriodicalWithdrawalMoney(IBillingService<User> iBillingService, ICrudService<User> userService) {
        this.iBillingService = iBillingService;
        this.userService = userService;
    }

    @Override
    @Scheduled(cron = "${application.cron.expression:0 0 0 L * *}")
    public void periodicalWithdrawingMoney() {

        userService.getAll().forEach(user -> {
            BigDecimal tariffCost = getTariffCostForUser(user);
            try {
                iBillingService.billForServices(user, tariffCost);
            } catch (MoneyNotFoundException e) {
                logger.error("Money not found for user: " + user.getUserId(), e);
            } catch (InsufficientFundsException e) {
                logger.error("Insufficient funds for user: " + user.getUserId(), e);
            }
        });
    }

    private BigDecimal getTariffCostForUser(User user) {
        Tariff tariff = user.getTariff();

        if (tariff != null) {
            return tariff.getPrice();
        } else {
            return BigDecimal.ZERO;
        }
    }
}
