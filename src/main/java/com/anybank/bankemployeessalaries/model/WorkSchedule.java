package com.anybank.bankemployeessalaries.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

@Slf4j
@Data
@Builder
public class WorkSchedule {
    private int id;
    @NotBlank
    private int numberOfWorkDays;
    @NotBlank
    private int numberOfWeekDays;
    @NotBlank
    private int numberOfHours;
}
