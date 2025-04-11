package com.uutrunk.hospitalordermanagement.Enum;

public enum OrderType {
    临时("临时"),
    长期("长期");

    private String value;

    OrderType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
