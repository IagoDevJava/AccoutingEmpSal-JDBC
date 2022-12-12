package com.anybank.bankemployeessalaries.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
public class SalariesData {
    private int id;
    private Grade grade;
    private int wage;
    private double personalKpi;
    private double teamKpi;
    private double commonKpi;
}