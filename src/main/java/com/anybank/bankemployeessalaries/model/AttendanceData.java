package com.anybank.bankemployeessalaries.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AttendanceData {
    private long id;
    private LocalDate dateAtt;
    private int employeeId;
    private Status status;
}
