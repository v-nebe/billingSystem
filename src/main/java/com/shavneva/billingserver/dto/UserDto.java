package com.shavneva.billingserver.dto;
import com.shavneva.billingserver.entities.User;
import lombok.Data;

@Data
public class UserDto  {
    Long userId;
    String firstName;
    String lastName;
    String email;
    String number;
    String password;
}
