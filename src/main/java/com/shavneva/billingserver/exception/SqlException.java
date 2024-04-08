package com.shavneva.billingserver.exception;

public class SqlException extends RuntimeException{
    public SqlException(String message) {
        super(message);
    }
}
