package com.uutrunk.hospitallogin.exception;

public class RepeatedSendVerificationCodeException extends RuntimeException {
    public RepeatedSendVerificationCodeException(String message) {
        super(message);
    }
}
