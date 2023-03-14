package com.anybank.bankemployeessalaries.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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
