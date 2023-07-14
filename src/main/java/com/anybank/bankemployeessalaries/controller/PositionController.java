package com.anybank.bankemployeessalaries.controller;

import com.anybank.bankemployeessalaries.dto.PositionDto;
import com.anybank.bankemployeessalaries.model.Position;
import com.anybank.bankemployeessalaries.service.PositionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@Transactional(readOnly = true)
@AllArgsConstructor
@RequestMapping("/positions")
public class PositionController {
    private final PositionService positionService;

    /**
     * Добавление должности в БД
     */
    @Transactional
    @PostMapping
    public ResponseEntity<PositionDto> addPosition(@Valid @RequestBody Position position) {
        log.info("Добавлем должность № {} в БД", position.getId());
        return ResponseEntity.ok(positionService.addPosition(position));
    }

    /**
     * Обновление должности в БД
     */
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<PositionDto> updatePosition(@RequestBody Position position,
                                                      @PathVariable @PositiveOrZero Integer id) {
        log.info("Обновляем должность №{} в БД", position.getId());
        return ResponseEntity.ok(positionService.updatePosition(position, id));
    }

    /**
     * Удаление всех должностей из БД
     */
    @Transactional
    @DeleteMapping
    public ResponseEntity<Void> deletePositions() {
        log.info("Очищаем таблицу должностей");
        positionService.deletePositions();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удаление должности по id из БД
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePositionById(@PositiveOrZero @PathVariable Integer id) {
        log.info("Удаляем должность № {} из БД", id);
        positionService.deletePositionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Получение списка должностей из БД
     */
    @Transactional
    @GetMapping
    public ResponseEntity<List<PositionDto>> getPosition() {
        log.info("Получаем список всех должностей");
        return ResponseEntity.ok(positionService.getPosition());
    }

    /**
     * Получение должности по id
     */
    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<PositionDto> getPositionById(@PositiveOrZero @PathVariable Integer id) {
        log.info("Получаем должность № {}", id);
        return ResponseEntity.ok(positionService.getPositionById(id));
    }
}
