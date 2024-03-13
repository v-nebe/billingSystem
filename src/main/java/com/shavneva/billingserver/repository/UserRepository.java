package com.shavneva.billingserver.repository;

import java.util.Optional;
import com.shavneva.billingserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNumber(String number);
}
