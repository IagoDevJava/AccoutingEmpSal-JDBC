package com.anybank.bankemployeessalaries.repository;

import com.anybank.bankemployeessalaries.model.Kpi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KpiRepository extends JpaRepository<Kpi, Long> {
    Optional<Kpi> findByEmployeeAndMonthAndYear(Integer empId, String month, String year);
}