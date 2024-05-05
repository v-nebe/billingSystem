package com.shavneva.billingserver.service.impl.businesslogic;

import com.shavneva.billingserver.entities.Account;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.InsufficientFundsException;
import com.shavneva.billingserver.exception.MoneyNotFoundException;
import com.shavneva.billingserver.repository.AccountRepository;
import com.shavneva.billingserver.service.IBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@EnableScheduling
public class BillingService implements IBillingService<User> {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private NotificationService notificationService;

    //выставить счет пользователю и снять деньги за предоставленные услуги
    @Override
    public void billForServices(User user, BigDecimal tariffCost) {
        Account account = user.getAccount();
        if (account == null) {
            throw new MoneyNotFoundException("Account not found for user: " + user.getUserId());
        }

        BigDecimal currentAmount = account.getAmount();
        if (currentAmount.compareTo(tariffCost) < 0) {
            throw new InsufficientFundsException("Insufficient funds to pay for services");
        }

        account.setAmount(currentAmount.subtract(tariffCost));
        accountRepository.save(account);

        // Уведомление пользователя о списании средств
        notificationService.notifyUserAboutBalance(user.getEmail(), account.getAmount(), user.getNumber());
    }

    @Scheduled(cron="${my.cron.expression: 0 0 0 L * *}")
    public void periodicalWithdrawingMoney() {
        //billForServices(User , BigDecimal tariffCost)
        notificationService.notifyUserAboutBalance("adfad@sadfsa.com", BigDecimal.valueOf(13412341), "+375123412342");
    }

    //выполняет пополнение счета для пользователя
    @Override
    public void depositMoney(User user, BigDecimal amount) {
        Account account = user.getAccount();
        if (account == null) {
            account = new Account();
            account.setUser(user);
            account.setAmount(BigDecimal.ZERO);
        }

        account.setAmount(account.getAmount().add(amount));
        accountRepository.save(account);

        // Уведомление пользователя о пополнении счета
        notificationService.notifyUserAboutDeposit(user.getEmail(), amount, user.getNumber());
    }
}