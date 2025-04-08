package com.uutrunk.hospitallogin.dto;

import lombok.Data;

import java.util.List;

@Data
public class RolePermissionDTO {
    private String role;
    private List<String> permissions;
}