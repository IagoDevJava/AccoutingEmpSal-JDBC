package com.anybank.bankemployeessalaries.controller;

import com.anybank.bankemployeessalaries.model.Position;
import com.anybank.bankemployeessalaries.service.PositionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/positions")
public class PositionController {
    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }


    /**
     * Добавление должности в БД
     */
    @PostMapping
    public Position addPosition(@Valid @RequestBody Position position) {
        log.info("Добавлем должность № {} в БД", position.getId());
        return positionService.addPosition(position);
    }

    /**
     * Обновление должности в БД
     */
    @PutMapping
    public Position updatePosition(@Valid @RequestBody Position position) {
        log.info("Обновляем должность №{} в БД", position.getId());
        return positionService.updatePosition(position);
    }

    /**
     * Удаление всех должностей из БД
     */
    @DeleteMapping
    public void deletePositions() {
        log.info("Очищаем таблицу должностей");
        positionService.deletePositions();
    }

    /**
     * Удаление должности по id из БД
     */
    @DeleteMapping("/{id}")
    public void deletePositionById(@PathVariable String id) {
        log.info("Удаляем должность № {} из БД", id);
        positionService.deletePositionById(id);
    }

    /**
     * Получение списка должностей из БД
     */
    @GetMapping
    public List<Position> getPosition() {
        log.info("Получаем список всех должностей");
        return positionService.getPosition();
    }

    /**
     * Получение должности по id
     */
    @GetMapping("/{id}")
    public Position getPositionById(@PathVariable String id) {
        log.info("Получаем должность № {}", id);
        return positionService.getPositionById(id);
    }
}
