package com.anybank.bankemployeessalaries.controller;

import com.anybank.bankemployeessalaries.dto.GradeDto;
import com.anybank.bankemployeessalaries.model.Grade;
import com.anybank.bankemployeessalaries.service.GradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Transactional
    @PostMapping
    public ResponseEntity<GradeDto> addGrade(@Valid @RequestBody Grade grade) {
        log.info("Добавлем грейд № {} в БД", grade.getId());
        return ResponseEntity.ok(gradeService.addGrade(grade));
    }

    /**
     * Обновление грейда в БД
     */
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<GradeDto> updateGrade(@Valid @RequestBody Grade grade,
                                                @PathVariable @PositiveOrZero Integer id) {
        log.info("Обновляем грейд №{} в БД", grade.getId());
        return ResponseEntity.ok(gradeService.updateGrade(grade, id));
    }

    /**
     * Удаление всех грейдов из БД
     */
    @Transactional
    @DeleteMapping
    public ResponseEntity<Void> deleteGrades() {
        log.info("Очищаем таблицу грейдов");
        gradeService.deleteGrades();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удаление грейда по id из БД
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGradeById(@PositiveOrZero @PathVariable Integer id) {
        log.info("Удаляем грейд № {} из БД", id);
        gradeService.deleteGradeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Получение списка грейдов из БД
     */
    @Transactional
    @GetMapping
    public ResponseEntity<List<GradeDto>> getGrades() {
        log.info("Получаем список всех грейдов");
        return ResponseEntity.ok(gradeService.getGrades());
    }

    /**
     * Получение грейда по id
     */
    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<GradeDto> getGradeById(@PositiveOrZero @PathVariable Integer id) {
        log.info("Получаем грейд № {}", id);
        return ResponseEntity.ok(gradeService.getGradeById(id));
    }
}
