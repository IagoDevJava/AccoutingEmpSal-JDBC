package com.anybank.bankemployeessalaries.repository;

import com.anybank.bankemployeessalaries.model.Kpi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KpiRepository extends JpaRepository<Kpi, Long> {
}