package com.shavneva.billingserver.exception;

public class AuthorizationServiceException extends RuntimeException {
    public AuthorizationServiceException(String message) {
        super(message);
    }
}