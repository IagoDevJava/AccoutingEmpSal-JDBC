package com.anybank.bankemployeessalaries.repository;

import com.anybank.bankemployeessalaries.model.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Integer> {
}