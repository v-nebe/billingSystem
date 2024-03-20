package com.shavneva.billingserver.dto;
import com.shavneva.billingserver.entities.User;
import lombok.Data;

@Data
public class UserDto  {
    Integer userId;
    String firstName;
    String lastName;
    String email;
    String number;
    String password;
}
