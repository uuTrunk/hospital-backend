package com.uutrunk.hospitalordermanagement.exception;

public class OrderNotExistException extends RuntimeException {
    public OrderNotExistException(String message) {
        super(message);
    }
}
