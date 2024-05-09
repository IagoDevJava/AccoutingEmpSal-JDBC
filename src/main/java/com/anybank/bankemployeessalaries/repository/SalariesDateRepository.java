package com.anybank.bankemployeessalaries.repository;

import com.anybank.bankemployeessalaries.model.SalariesData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalariesDateRepository extends JpaRepository<SalariesData, Integer> {
    Optional<SalariesData> findByPosition(Integer id);
}