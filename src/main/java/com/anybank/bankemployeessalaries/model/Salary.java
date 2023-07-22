package com.anybank.bankemployeessalaries.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "salary")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    @Column(name = "department_id")
    Integer departmentId;
    @NotNull
    @Column(name = "employee_id")
    Integer employeeId;
    @NotBlank
    @Column
    String month;
    @NotBlank
    @Column
    String year;
    @NotNull
    @Column
    Double salary;

    public void setId(Long id) {
        if (id != null) this.id = id;
    }

    public void setDepartmentId(Integer departmentId) {
        if (departmentId != null) this.departmentId = departmentId;
    }

    public void setEmployeeId(Integer employeeId) {
        if (employeeId != null) this.employeeId = employeeId;
    }

    public void setMonth(String month) {
        if (month != null) this.month = month;
    }

    public void setYear(String year) {
        if (year != null) this.year = year;
    }

    public void setSalary(Double salary) {
        if (salary != null) this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salary salary1 = (Salary) o;
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