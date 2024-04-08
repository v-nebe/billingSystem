package com.shavneva.billingserver.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "money")
public class Money {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "money_id")
    private Integer moneyId;
    @Column(name = "amount")
    private double amount;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}