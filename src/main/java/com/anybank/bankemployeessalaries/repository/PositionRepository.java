package com.anybank.bankemployeessalaries.repository;

import com.anybank.bankemployeessalaries.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {
}