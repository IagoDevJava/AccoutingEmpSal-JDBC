//package com.anybank.bankemployeessalaries.controller;
//
//import com.anybank.bankemployeessalaries.model.Position;
//import com.anybank.bankemployeessalaries.service.PositionService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import javax.validation.constraints.PositiveOrZero;
//import java.util.List;
//
//@Slf4j
//@Validated
//@RestController
//@RequestMapping("/positions")
//public class PositionController {
//    private final PositionService positionService;
//
//    @Autowired
//    public PositionController(PositionService positionService) {
//        this.positionService = positionService;
//    }
//
//
//    /**
//     * Добавление должности в БД
//     */
//    @PostMapping
//    public ResponseEntity<Position> addPosition(@Valid @RequestBody Position position) {
//        log.info("Добавлем должность № {} в БД", position.getId());
//        return ResponseEntity.ok(positionService.addPosition(position));
//    }
//
//    /**
//     * Обновление должности в БД
//     */
//    @PutMapping
//    public ResponseEntity<Position> updatePosition(@RequestBody Position position) {
//        log.info("Обновляем должность №{} в БД", position.getId());
//        return ResponseEntity.ok(positionService.updatePosition(position));
//    }
//
//    /**
//     * Удаление всех должностей из БД
//     */
//    @DeleteMapping
//    public ResponseEntity<Void> deletePositions() {
//        log.info("Очищаем таблицу должностей");
//        positionService.deletePositions();
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    /**
//     * Удаление должности по id из БД
//     */
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePositionById(@PositiveOrZero @PathVariable Integer id) {
//        log.info("Удаляем должность № {} из БД", id);
//        positionService.deletePositionById(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    /**
//     * Получение списка должностей из БД
//     */
//    @GetMapping
//    public ResponseEntity<List<Position>> getPosition() {
//        log.info("Получаем список всех должностей");
//        return ResponseEntity.ok(positionService.getPosition());
//    }
//
//    /**
//     * Получение должности по id
//     */
//    @GetMapping("/{id}")
//    public ResponseEntity<Position> getPositionById(@PositiveOrZero @PathVariable Integer id) {
//        log.info("Получаем должность № {}", id);
//        return ResponseEntity.ok(positionService.getPositionById(id));
//    }
//}
