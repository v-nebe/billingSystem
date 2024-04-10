package com.shavneva.billingserver.repository;

import com.shavneva.billingserver.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
