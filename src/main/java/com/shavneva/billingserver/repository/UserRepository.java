package com.shavneva.billingserver.repository;

import java.util.Optional;
import com.shavneva.billingserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
   User findByNumber(String number);
}
