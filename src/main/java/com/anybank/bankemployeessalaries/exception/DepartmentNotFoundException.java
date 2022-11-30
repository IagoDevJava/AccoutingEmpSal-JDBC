package com.anybank.bankemployeessalaries.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String format) {
        super(format);
    }
}
