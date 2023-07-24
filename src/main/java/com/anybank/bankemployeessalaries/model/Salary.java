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
@Builder
@Entity
@Table(name = "salary")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    @Column(name = "employee_id")
    Integer employeeId;
    @NotNull
    @Column(name = "department_id")
    Integer departmentId;
    @NotBlank
    @Column
    String month;
    @NotBlank
    @Column
    String year;
    @NotNull
    @Column
    Double payment;

    public void setId(Long id) {
        if (id != null) this.id = id;
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

    public void setPayment(Double payment) {
        if (payment != null) this.payment = payment;
    }

    public void setDepartmentId(Integer departmentId) {
        if (departmentId != null) this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salary salary = (Salary) o;
        return Objects.equals(id, salary.id) && Objects.equals(employeeId, salary.employeeId) && Objects.equals(departmentId, salary.departmentId) && Objects.equals(month, salary.month) && Objects.equals(year, salary.year) && Objects.equals(payment, salary.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, departmentId, month, year, payment);
    }
}