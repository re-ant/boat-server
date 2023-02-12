package com.reant.boat.domain.enums.error;

public enum ErrorCode {
    DUPLICATE_USER_ID(409),
    DUPLICATE_USER_NAME(409)
    ;

    private final int value;
    ErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}