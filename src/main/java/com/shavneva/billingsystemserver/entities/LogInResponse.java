package com.shavneva.billingsystemserver.entities;

import org.springframework.security.core.userdetails.UserDetails;

public class LogInResponse {
    String errorMessage;
    UserDetails user;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }
}
