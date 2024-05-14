package com.shavneva.billingserver.repository;

import com.shavneva.billingserver.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByUserId(Long userId);
}
