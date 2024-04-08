package com.shavneva.billingserver.exception;

public class MoneyNotFoundException extends RuntimeException {
    public MoneyNotFoundException(String message) {
        super(message);
    }
}