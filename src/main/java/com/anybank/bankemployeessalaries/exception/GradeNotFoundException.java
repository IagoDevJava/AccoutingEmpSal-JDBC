package com.anybank.bankemployeessalaries.exception;

public class GradeNotFoundException extends RuntimeException {
    public GradeNotFoundException(String format) {
        super(format);
    }
}
