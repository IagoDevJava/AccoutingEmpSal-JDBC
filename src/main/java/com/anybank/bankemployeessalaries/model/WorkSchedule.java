package com.anybank.bankemployeessalaries.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
public class WorkSchedule {
    private int id;
    private int numberOfWorkDays;
    private int numberOfWeekDays;
    private int numberOfHours;
}
