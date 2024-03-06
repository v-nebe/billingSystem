package com.shavneva.billingsystemserver.repository;

import java.util.Optional;
import com.shavneva.billingsystemserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNumber(String number);
}
