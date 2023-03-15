package com.anybank.bankemployeessalaries.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
public class Salary {
    private Long id;
    @NotBlank
    private Integer departmentId;
    @NotBlank
    private Integer employeeId;
    @NotBlank
    private String month;
    @NotBlank
    private String year;
    @NotBlank
    private Double salary;
}