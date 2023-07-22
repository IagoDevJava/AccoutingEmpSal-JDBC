package com.anybank.bankemployeessalaries.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalaryDto {
    Long id;
    Integer departmentId;
    Integer employeeId;
    String month;
    String year;
    Double salary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryDto salary1 = (SalaryDto) o;
        return Objects.equals(id, salary1.id)
                && Objects.equals(departmentId, salary1.departmentId)
                && Objects.equals(employeeId, salary1.employeeId)
                && Objects.equals(month, salary1.month)
                && Objects.equals(year, salary1.year)
                && Objects.equals(salary, salary1.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departmentId, employeeId, month, year, salary);
    }
}