package com.anybank.bankemployeessalaries.repository;

import com.anybank.bankemployeessalaries.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}