package com.anybank.bankemployeessalaries.controller;

import com.anybank.bankemployeessalaries.model.Grade;
import com.anybank.bankemployeessalaries.service.GradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/grades")
public class GradeController {
    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    /**
     * Добавление грейда в БД
     */
    @PostMapping
    public ResponseEntity<Grade> addGrade(@Valid @RequestBody Grade grade) {
        log.info("Добавлем грейд № {} в БД", grade.getId());
        return ResponseEntity.ok(gradeService.addGrade(grade));
    }

    /**
     * Обновление грейда в БД
     */
    @PutMapping
    public ResponseEntity<Grade> updateGrade(@Valid @RequestBody Grade grade) {
        log.info("Обновляем грейд №{} в БД", grade.getId());
        return ResponseEntity.ok(gradeService.updateGrade(grade));
    }

    /**
     * Удаление всех грейдов из БД
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteGrades() {
        log.info("Очищаем таблицу грейдов");
        gradeService.deleteGrades();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удаление грейда по id из БД
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGradeById(@PositiveOrZero @PathVariable Integer id) {
        log.info("Удаляем грейд № {} из БД", id);
        gradeService.deleteGradeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Получение списка грейдов из БД
     */
    @GetMapping
    public ResponseEntity<List<Grade>> getGrades() {
        log.info("Получаем список всех грейдов");
        return ResponseEntity.ok(gradeService.getGrades());
    }

    /**
     * Получение грейда по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGradeById(@PositiveOrZero @PathVariable Integer id) {
        log.info("Получаем грейд № {}", id);
        return ResponseEntity.ok(gradeService.getGradeById(id));
    }
}
