package com.uutrunk.hospitallogin.dto;

import lombok.Data;

@Data
public class UpdateUserStatusDTO {
    private Long userId;
    private String status;
}