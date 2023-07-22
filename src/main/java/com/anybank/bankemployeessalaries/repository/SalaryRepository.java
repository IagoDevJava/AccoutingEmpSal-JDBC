package com.anybank.bankemployeessalaries.repository;

import com.anybank.bankemployeessalaries.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
}