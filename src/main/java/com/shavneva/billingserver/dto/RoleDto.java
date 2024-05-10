package com.shavneva.billingserver.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


public class RoleDto {
    @Getter
    @Setter
    Integer roleId;

    @NotBlank
    @Getter
    @Setter
    String roleName;
}