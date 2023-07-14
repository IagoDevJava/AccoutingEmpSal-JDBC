package com.anybank.bankemployeessalaries.repository;

import com.anybank.bankemployeessalaries.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
}