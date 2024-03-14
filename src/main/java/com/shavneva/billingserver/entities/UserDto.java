package com.shavneva.billingserver.entities;
import lombok.Data;

@Data
public class UserDto {
    Long userId;
    String firstName;
    String lastName;
    String email;
    String number;
    String password;
}
