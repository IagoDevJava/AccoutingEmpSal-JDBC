package com.anybank.bankemployeessalaries.dto;

import com.anybank.bankemployeessalaries.enum_model.AttendanceStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttendanceDataDto {
    Long id;
    LocalDateTime dateAtt;
    Integer employeeId;
    AttendanceStatus attendanceStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceDataDto that = (AttendanceDataDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(dateAtt, that.dateAtt)
                && Objects.equals(employeeId, that.employeeId)
                && attendanceStatus == that.attendanceStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateAtt, employeeId, attendanceStatus);
    }
}