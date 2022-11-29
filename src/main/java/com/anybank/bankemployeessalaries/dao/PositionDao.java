package com.anybank.bankemployeessalaries.dao;

import com.anybank.bankemployeessalaries.model.Position;

public interface PositionDao {
    Position findPositionById(int position_id);
}
