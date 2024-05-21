package com.shavneva.billingserver.service.impl;

import com.shavneva.billingserver.entities.Account;
import com.shavneva.billingserver.entities.Role;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.repository.RoleRepository;
import com.shavneva.billingserver.repository.TariffRepository;
import com.shavneva.billingserver.repository.UserRepository;
import com.shavneva.billingserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shavneva.billingserver.entities.Tariff;

import java.util.Collections;

@Service
public class UserService extends BaseService<User, Integer> {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final TariffRepository tariffRepository;

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

        // Сохраняем обновленную сущность
        return userRepository.save(existingEntity);
    }

    @Override
    public User create(User user) {
        // Set default tariff
        Tariff defaultTariff = tariffRepository.findById(3)
                .orElseThrow(() -> new IllegalStateException("Default tariff not found"));
        user.setTariff(defaultTariff);

        // Create new account
        Account newAccount = new Account();
        newAccount = accountRepository.save(newAccount);
        user.setAccount(newAccount);

        // Set default notification type
        user.setNotificationType("email");

        // Set default role
        Role userRole = roleRepository.findByRoleName("ROLE_USER");
        if (userRole != null) {
            user.setRoles(Collections.singleton(userRole));
        } else {
            throw new IllegalStateException("Role ROLE_USER not found");
        }

        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}