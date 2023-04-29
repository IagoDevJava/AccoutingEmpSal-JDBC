package com.anybank.bankemployeessalaries.dto;

import com.anybank.bankemployeessalaries.enum_model.JobStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDto {
    Integer id;
    String surname;
    String firstname;
    String lastname;
    String gender;
    Integer departmentId;
    String phone;
    String email;
    Integer positionId;
    Integer workScheduleId;
    LocalDateTime dateOfAdmission;
    LocalDateTime dateOfDismissal;
    JobStatus jobStatus;
}
