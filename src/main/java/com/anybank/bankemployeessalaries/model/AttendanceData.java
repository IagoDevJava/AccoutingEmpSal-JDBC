package com.anybank.bankemployeessalaries.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AttendanceData {
    private long id;
    @NotBlank
    private LocalDate dateAtt;
    @NotBlank
    @PositiveOrZero
    private int employeeId;
    @NotBlank
    private Status status;
}
