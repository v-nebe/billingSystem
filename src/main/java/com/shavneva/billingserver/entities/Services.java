package com.shavneva.billingserver.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.ToDoubleBiFunction;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "services")
public class Services extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;
    @Column(name = "service")
    private String service;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "services")
    private Collection<Tariff> tariffs = new HashSet<>();

    @Override
    public Integer getId() {
        return serviceId;
    }
}
