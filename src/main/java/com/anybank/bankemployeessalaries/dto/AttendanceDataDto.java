package com.anybank.bankemployeessalaries.dto;

import com.anybank.bankemployeessalaries.enum_model.JobStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Objects;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttendanceDataDto {
    Long id;
    LocalDate dateAtt;
    Integer employeeId;
    JobStatus jobStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceDataDto that = (AttendanceDataDto) o;
        return Objects.equals(id, that.id) && Objects.equals(dateAtt, that.dateAtt) && Objects.equals(employeeId, that.employeeId) && jobStatus == that.jobStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateAtt, employeeId, jobStatus);
    }
}