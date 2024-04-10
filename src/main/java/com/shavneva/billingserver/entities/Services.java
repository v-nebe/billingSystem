package com.shavneva.billingserver.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashSet;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "services")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;
    @Column(name = "service")
    private String service;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "services")
    private Collection<Tariff> tariffs = new HashSet<>();
}
