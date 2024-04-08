package com.shavneva.billingserver.repository;

public class MoneyNotFoundException extends RuntimeException {
    public MoneyNotFoundException(String message) {
        super(message);
    }
}