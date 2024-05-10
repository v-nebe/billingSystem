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
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = " account_id")
    private Integer accountId;
    @Column(name = "amount")
    private BigDecimal amount;

    @ToString.Exclude
    @OneToOne(mappedBy = "account")
    private User user;
}