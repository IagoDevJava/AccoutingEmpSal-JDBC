package com.anybank.bankemployeessalaries.dto;

import com.anybank.bankemployeessalaries.enum_model.JobStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto that = (EmployeeDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(surname, that.surname)
                && Objects.equals(firstname, that.firstname)
                && Objects.equals(lastname, that.lastname)
                && Objects.equals(gender, that.gender)
                && Objects.equals(departmentId, that.departmentId)
                && Objects.equals(phone, that.phone)
                && Objects.equals(email, that.email)
                && Objects.equals(positionId, that.positionId)
                && Objects.equals(workScheduleId, that.workScheduleId)
                && Objects.equals(dateOfAdmission, that.dateOfAdmission)
                && Objects.equals(dateOfDismissal, that.dateOfDismissal)
                && jobStatus == that.jobStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, firstname, lastname, gender,
                departmentId, phone, email, positionId, workScheduleId, dateOfAdmission, dateOfDismissal, jobStatus);
    }
}
