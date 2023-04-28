package com.anybank.bankemployeessalaries.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

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