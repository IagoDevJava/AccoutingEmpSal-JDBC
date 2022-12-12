package com.anybank.bankemployeessalaries.controller;

import com.anybank.bankemployeessalaries.model.Grade;
import com.anybank.bankemployeessalaries.service.GradeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
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
    public Grade addGrade(@Valid @RequestBody Grade grade) {
        log.info("Добавлем грейд № {} в БД", grade.getId());
        return gradeService.addGrade(grade);
    }

    /**
     * Обновление грейда в БД
     */
    @PutMapping
    public Grade updateGrade(@Valid @RequestBody Grade grade) {
        log.info("Обновляем грейд №{} в БД", grade.getId());
        return gradeService.updateGrade(grade);
    }

    /**
     * Удаление всех грейдов из БД
     */
    @DeleteMapping
    public void deleteGrades() {
        log.info("Очищаем таблицу грейдов");
        gradeService.deleteGrades();
    }

    /**
     * Удаление грейда по id из БД
     */
    @DeleteMapping("/{id}")
    public void deleteGradeById(@PathVariable String id) {
        log.info("Удаляем грейд № {} из БД", id);
        gradeService.deleteGradeById(id);
    }

    /**
     * Получение списка грейдов из БД
     */
    @GetMapping
    public List<Grade> getGrades() {
        log.info("Получаем список всех грейдов");
        return gradeService.getGrades();
    }

    /**
     * Получение грейда по id
     */
    @GetMapping("/{id}")
    public Grade getGradeById(@PathVariable int id) {
        log.info("Получаем грейд № {}", id);
        return gradeService.getGradeById(id);
    }
}
