package com.shavneva.billingserver.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Getter
    @Setter
    @Column(name = "role_name")
    private String roleName;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Collection<User> users = new HashSet<>();
}
