package com.shavneva.billingserver.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscriber")
public class Subscriber {
    @Id
    @GeneratedValue
    @Column(name = "subscriber_id")
    private Integer subscriberId;
    @Column(name = "amount")
    private String amount;
}
