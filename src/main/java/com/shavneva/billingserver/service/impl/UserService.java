package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.Account;
import com.shavneva.billingserver.entities.Role;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.repository.RoleRepository;
import com.shavneva.billingserver.repository.TariffRepository;
import com.shavneva.billingserver.repository.UserRepository;
import com.shavneva.billingserver.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shavneva.billingserver.entities.Tariff;

import java.math.BigDecimal;
import java.util.Collections;

import static com.mysql.cj.conf.PropertyKey.logger;

@Service
public class UserService extends BaseService<User, Integer> {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final TariffRepository tariffRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, AccountRepository accountRepository, RoleRepository roleRepository, TariffRepository tariffRepository) {
        super(userRepository);
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.tariffRepository = tariffRepository;
    }

    @Override
    public User update(User updatedEntity) {
        // Получаем оригинальную сущность из базы данных
        User existingEntity = userRepository.findById(updatedEntity.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("User not found with id: " + updatedEntity.getUserId())
        );

        // Обновляем только измененные поля
        if (updatedEntity.getFirstName() != null) {
            existingEntity.setFirstName(updatedEntity.getFirstName());
        }
        if (updatedEntity.getLastName() != null) {
            existingEntity.setLastName(updatedEntity.getLastName());
        }
        if (updatedEntity.getEmail() != null) {
            existingEntity.setEmail(updatedEntity.getEmail());
        }
        if (updatedEntity.getNumber() != null) {
            existingEntity.setNumber(updatedEntity.getNumber());
        }
        if (updatedEntity.getPassword() != null) {
            existingEntity.setPassword(updatedEntity.getPassword());
        }
        if (updatedEntity.getAccount() != null) {
            existingEntity.setAccount(updatedEntity.getAccount());
        }
        if (updatedEntity.getTariff() != null) {
            existingEntity.setTariff(updatedEntity.getTariff());
        }
        if (updatedEntity.getRoles() != null && !updatedEntity.getRoles().isEmpty()) {
            existingEntity.setRoles(updatedEntity.getRoles());
        }
        if (updatedEntity.getNotificationType() != null) {
            existingEntity.setNotificationType(updatedEntity.getNotificationType());
        }

        // Сохраняем обновленную сущность
        return userRepository.save(existingEntity);
    }

    @Override
    public User create(User user) {
        logger.info("Attempting to create a new user with email: {}", user.getEmail());

        Tariff defaultTariff = tariffRepository.findById(3)
                .orElseThrow(() -> new IllegalStateException("Default tariff not found"));
        logger.info("Default tariff found: {}", defaultTariff);
        user.setTariff(defaultTariff);

        Account newAccount = new Account();
        newAccount.setAmount(BigDecimal.valueOf(0));
        newAccount = accountRepository.save(newAccount);
        logger.info("New account created with ID: {}", newAccount.getId());
        user.setAccount(newAccount);

        user.setNotificationType("email");
        logger.info("Notification type set to: email");

        Role userRole = roleRepository.findByRoleName("ROLE_USER");
        if (userRole != null) {
            user.setRoles(Collections.singleton(userRole));
            logger.info("User role set to: ROLE_USER");
        } else {
            throw new IllegalStateException("Role ROLE_USER not found");
        }

        User savedUser = userRepository.save(user);
        logger.info("User created successfully with ID: {}", savedUser.getId());
        return savedUser;
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}