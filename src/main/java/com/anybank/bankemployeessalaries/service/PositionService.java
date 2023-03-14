package com.anybank.bankemployeessalaries.service;

import com.anybank.bankemployeessalaries.dao.PositionDao;
import com.anybank.bankemployeessalaries.model.Position;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    private final PositionDao positionDao;

    @Autowired
    public PositionService(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    /**
     * Добавление должности в БД
     */
    public Position addPosition(Position position) {
        return positionDao.addPosition(position);
    }

    /**
     * Обновление должности в БД
     */
    public Position updatePosition(Position position) {
        return positionDao.updatePosition(position);
    }

    /**
     * Удаление всех должностей из БД
     */
    public void deletePositions() {
        positionDao.deletePositions();
    }

    /**
     * Удаление должности по id из БД
     */
    public void deletePositionById(@PositiveOrZero Integer id) {
        positionDao.deletePositionById(id);
    }

    /**
     * Получение списка должностей из БД
     */
    public List<Position> getPosition() {
        return positionDao.getPosition();
    }

    /**
     * Получение должности по id
     */
    public Position getPositionById(@PositiveOrZero Integer id) {
        return positionDao.findPositionById(id);
    }
}
