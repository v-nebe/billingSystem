package com.shavneva.billingserver.repository;


import com.shavneva.billingserver.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Services, Integer> {
}