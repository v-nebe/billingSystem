package com.shavneva.billingserver.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public abstract class BaseEntity<ID> {
    private ID id;

    // Геттеры и сеттеры для id

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}