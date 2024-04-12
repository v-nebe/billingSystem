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
@Table(name = "tariff")
public class Tariff extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tariff_id")
    private Integer tariffId;
    @Column(name = "tariff_name")
    private String tariffName;
    @Column(name = "price")
    private String price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "service_tariffs",
            joinColumns = @JoinColumn(
                    name = "tariff_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "service_id"))
    private Collection<Services> services = new HashSet<>();

    @Override
    public Integer getId() {
        return tariffId;
    }
}
