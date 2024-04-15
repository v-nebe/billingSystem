package com.shavneva.billingserver.exception;

public class InsufficientFundsException extends BaseException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}