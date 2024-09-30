package com.anybank.repository;

import com.anybank.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
    Optional<Salary> findByEmployeeAndMonthAndYear(Integer employeeId, String month, String year);

    List<Salary> findByEmployeeAndYear(Integer employeeId, String year);

    List<Salary> findByDepartmentAndMonthAndYear(Integer departmentId, String month, String year);

    List<Salary> findByDepartmentAndYear(Integer departmentId, String year);

    List<Salary> findByMonthAndYear(String month, String year);

    List<Salary> findByYear(String year);
}