package com.anybank.bankemployeessalaries.exception;

public class SalaryNotFoundException extends RuntimeException {
    public SalaryNotFoundException(String message) {
        super(message);
    }
}
