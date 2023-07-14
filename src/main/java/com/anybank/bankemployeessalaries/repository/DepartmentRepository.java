package com.anybank.bankemployeessalaries.repository;

import com.anybank.bankemployeessalaries.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}