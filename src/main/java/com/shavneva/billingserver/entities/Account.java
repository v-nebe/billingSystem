package com.shavneva.billingserver.entities;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "money_id")
    private Integer moneyId;
    @Column(name = "amount")
    private BigDecimal amount;

    @ToString.Exclude
    @OneToOne(mappedBy = "account")
    private User user;
}