package com.uutrunk.hospitallogin.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String account;
    private String password;
}