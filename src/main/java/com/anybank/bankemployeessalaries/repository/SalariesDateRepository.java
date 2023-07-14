package com.anybank.bankemployeessalaries.repository;

import com.anybank.bankemployeessalaries.model.SalariesData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalariesDateRepository extends JpaRepository<SalariesData, Integer> {
}