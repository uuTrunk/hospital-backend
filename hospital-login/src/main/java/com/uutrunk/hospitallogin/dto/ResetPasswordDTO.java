package com.uutrunk.hospitallogin.dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private String phoneNumber;
    private String verificationCode;
    private String newPassword;
}