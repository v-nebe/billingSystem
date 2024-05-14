package com.shavneva.billingserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    private UserDto user;
    private TariffDto tariff;
    private AccountDto account;
}
