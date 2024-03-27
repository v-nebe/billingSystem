package com.shavneva.billingserver.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.HashSet;

@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Collection<User> users = new HashSet<>();

}
