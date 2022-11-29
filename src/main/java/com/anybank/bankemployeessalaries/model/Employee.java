package com.anybank.bankemployeessalaries.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
@Data
@Builder
public class Employee {
    private int id;
    private String surname;
    private String firstname;
    private String lastname;
    private String gender;
    private Department department;
    private String phone;
    private String email;
    private Position position;
    private WorkSchedule workSchedule;
    private LocalDate dateOfAdmission;
    private LocalDate dateOfDismissal;
}
