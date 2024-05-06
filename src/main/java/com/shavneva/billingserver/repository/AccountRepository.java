package com.shavneva.billingserver.repository;

import com.shavneva.billingserver.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUserId(Long userId);
}
