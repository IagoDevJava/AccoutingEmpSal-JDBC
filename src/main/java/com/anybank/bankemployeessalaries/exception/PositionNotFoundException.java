package com.anybank.bankemployeessalaries.exception;

public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException(String format) {
        super(format);
    }
}
