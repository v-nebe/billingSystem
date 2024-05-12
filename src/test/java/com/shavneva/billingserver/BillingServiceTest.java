package com.shavneva.billingserver;

import com.shavneva.billingserver.entities.Account;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.exception.InsufficientFundsException;
import com.shavneva.billingserver.exception.MoneyNotFoundException;
import com.shavneva.billingserver.repository.AccountRepository;
import com.shavneva.billingserver.repository.UserRepository;
import com.shavneva.billingserver.service.impl.businesslogic.BillingService;
import com.shavneva.billingserver.service.impl.businesslogic.NotificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BillingServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private BillingService billingService;

    @Test
    public void testBillForServices_sufficientFunds() {
        User user = new User();
        user.setEmail("example@example.com");
        user.setNumber("1234567890");
        Account account = new Account();
        account.setAmount(BigDecimal.valueOf(100));
        user.setAccount(account);

        BigDecimal amountToBill = BigDecimal.valueOf(50);

        billingService.billForServices(user, amountToBill);

        verify(accountRepository, times(1)).save(any(Account.class));
        verify(notificationService, times(1)).notifyUserAboutBalance(eq("example@example.com"),
                eq(BigDecimal.valueOf(50)), eq("1234567890"));
    }


    @Test(expected = MoneyNotFoundException.class)
    public void testBillForServices_noAccount() {
        User user = new User();

        BigDecimal amountToBill = BigDecimal.valueOf(50);

        billingService.billForServices(user, amountToBill);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testBillForServices_insufficientFunds() {
        User user = new User();
        Account account = new Account();
        account.setAmount(BigDecimal.valueOf(30));
        user.setAccount(account);

        BigDecimal amountToBill = BigDecimal.valueOf(50);

        billingService.billForServices(user, amountToBill);
    }

    @Test
    public void testDepositMoney() {
        String userEmail = "example@example.com";
        String userNumber = "1234567890";
        BigDecimal initialAmount = BigDecimal.valueOf(100);
        BigDecimal amountToDeposit = BigDecimal.valueOf(50);

        User user = new User();
        user.setEmail(userEmail);
        user.setNumber(userNumber);

        Account account = new Account();
        account.setAmount(initialAmount);
        user.setAccount(account);

        billingService.depositMoney(userEmail, amountToDeposit);

        verify(accountRepository, times(1)).save(any(Account.class));
        verify(notificationService, times(1)).notifyUserAboutDeposit(eq(userEmail), eq(amountToDeposit), eq(userNumber));
    }
    }
}