package com.anybank.bankemployeessalaries.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Slf4j
@Data
@Builder
public class Employee {
    private int id;
    @NotBlank
    private String surname;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String gender;
    private Department department;
    private String phone;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private Position position;
    @NotBlank
    private WorkSchedule workSchedule;
    @NotBlank
    @DateTimeFormat
    private LocalDate dateOfAdmission;
    @DateTimeFormat
    private LocalDate dateOfDismissal;
}
