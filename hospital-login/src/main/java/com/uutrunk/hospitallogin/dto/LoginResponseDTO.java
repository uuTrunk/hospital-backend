package com.uutrunk.hospitallogin.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private String role;
    private Long userId;
}