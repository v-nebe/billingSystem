package com.shavneva.billingserver.service.impl.businesslogic;

import com.shavneva.billingserver.entities.Account;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.InsufficientFundsException;
import com.shavneva.billingserver.exception.MoneyNotFoundException;
import com.shavneva.billingserver.repository.AccountRepository;
import com.shavneva.billingserver.service.IBillingService;
import com.shavneva.billingserver.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@EnableScheduling
public class BillingService implements IBillingService<User> {

    private final AccountRepository accountRepository;
    private final INotificationService InotificationService;

    @Autowired
    public BillingService(AccountRepository accountRepository, INotificationService InotificationService) {
        this.accountRepository = accountRepository;
        this.InotificationService = InotificationService;
    }

    //выставить счет пользователю и снять деньги за предоставленные услуги
    @Override
    public void billForServices(User user, BigDecimal tariffCost) {
        Account account = user.getAccount();
        if (account == null) {
            throw new MoneyNotFoundException("Account not found for user: " + user.getUserId());
        }

        BigDecimal currentAmount = account.getAmount();
        if (currentAmount.compareTo(tariffCost) < 0) {
            InotificationService.notifyUserInsufficientFunds(user.getEmail(), user.getNumber());
            throw new InsufficientFundsException("Insufficient funds to pay for services");
        }

        account.setAmount(currentAmount.subtract(tariffCost));
        accountRepository.save(account);

        // Уведомление пользователя о списании средств
        InotificationService.notifyUserAboutBalance(user.getEmail(), account.getAmount(), user.getNumber());
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
        InotificationService.notifyUserAboutDeposit(user.getEmail(), amount, user.getNumber());
    }
}