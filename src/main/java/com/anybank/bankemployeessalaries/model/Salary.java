package com.anybank.bankemployeessalaries.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
@Data
@Builder
public class Salary {
    private int id;
    private int employeeId;
    private LocalDate monthYear;
    private int salary;
}