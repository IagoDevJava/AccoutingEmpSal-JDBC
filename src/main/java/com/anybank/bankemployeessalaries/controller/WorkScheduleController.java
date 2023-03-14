package com.anybank.bankemployeessalaries.controller;

import com.anybank.bankemployeessalaries.model.WorkSchedule;
import com.anybank.bankemployeessalaries.service.WorkScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/work-schedules")
public class WorkScheduleController {
    private final WorkScheduleService workScheduleService;

    @Autowired
    public WorkScheduleController(WorkScheduleService workScheduleService) {
        this.workScheduleService = workScheduleService;
    }

    /**
     * Добавить график в БД
     */
    @PostMapping()
    public ResponseEntity<WorkSchedule> addSchedule(@Valid @RequestBody WorkSchedule workSchedule) {
        log.info("Добавляем график работы с {} рабочими днями", workSchedule.getNumberOfWorkDays());
        return ResponseEntity.ok(workScheduleService.addSchedule(workSchedule));
    }

    /**
     * Изменить график в БД
     */
    @PutMapping
    public ResponseEntity<WorkSchedule> updateSchedule(@RequestBody WorkSchedule workSchedule) {
        log.info("Обновляем график работы с {} рабочими днями", workSchedule.getNumberOfWorkDays());
        return ResponseEntity.ok(workScheduleService.updateSchedule(workSchedule));
    }

    /**
     * Удалить графики из БД
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteSchedules() {
        log.info("Удаляем все графики из БД");
        workScheduleService.deleteSchedules();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удалить график в БД по id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduleById(@PositiveOrZero @PathVariable Integer id) {
        log.info("Удаляем график работы с № {}", id);
        workScheduleService.deleteScheduleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Получить все графики в БД
     */
    @GetMapping
    public ResponseEntity<List<WorkSchedule>> getSchedules() {
        log.info("Запросили все графики из БД");
        return ResponseEntity.ok(workScheduleService.getSchedules());
    }

    /**
     * Получить график в БД по id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<WorkSchedule> getScheduleById(@PositiveOrZero @PathVariable Integer id) {
        log.info("Запросили график работы с № {}", id);
        return ResponseEntity.ok(workScheduleService.getScheduleById(id));
    }
}
