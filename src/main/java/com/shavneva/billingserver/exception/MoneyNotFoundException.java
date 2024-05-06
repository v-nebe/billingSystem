package com.shavneva.billingserver.exception;

public class MoneyNotFoundException extends BaseException {
    public MoneyNotFoundException(String message) {
        super(message);
    }
}