package com.anybank.bankemployeessalaries.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
public class SalariesData {
    private int id;
    @NotBlank
    private Grade grade;
    @NotBlank
    private int wage;
    private double personalKpi;
    private double teamKpi;
    private double commonKpi;
}