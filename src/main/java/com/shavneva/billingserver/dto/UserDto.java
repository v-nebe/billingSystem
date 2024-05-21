package com.shavneva.billingserver.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

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
    @Size(min = 5)
    String password;

    TariffDto tariff;

    AccountDto account;

    String notificationType;

    private List<RoleDto> roles;
}
