package com.shavneva.billingserver.repository;


import com.shavneva.billingserver.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Integer> {
    @Query("SELECT s FROM Services s JOIN s.tariffs t WHERE t.id = :tariffId")
    List<Services> findByTariffId(Long tariffId);
}