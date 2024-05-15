package com.shavneva.billingserver.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto  {
    Integer userId;
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @NotBlank
    @Email
    String email;
    @NotBlank
    String number;
    @NotBlank
    @Size(min = 6)
    String password;

    TariffDto tariff;

    AccountDto account;

    RoleDto role;
}
