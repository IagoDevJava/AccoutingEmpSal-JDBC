package com.anybank.bankemployeessalaries.repository;

import com.anybank.bankemployeessalaries.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
    Optional<Salary> findByEmployeeIdAndMonthAndYear(Integer employeeId, String month, String year);

    List<Salary> findByEmployeeIdAndYear(Integer employeeId, String year);

    List<Salary> findByDepartmentIdAndMonthAndYear(Integer departmentId, String month, String year);

    List<Salary> findByDepartmentIdAndYear(Integer departmentId, String year);

    List<Salary> findByMonthAndYear(String month, String year);

    List<Salary> findByYear(String year);
}