package com.anybank.bankemployeessalaries.controller;

import com.anybank.bankemployeessalaries.model.WorkSchedule;
import com.anybank.bankemployeessalaries.service.WorkScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/work-schedule")
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
    public WorkSchedule addSchedule(@RequestBody WorkSchedule workSchedule) {
        log.info("Добавляем график работы с {} рабочими днями", workSchedule.getNumberOfWorkDays());
        return workScheduleService.addSchedule(workSchedule);
    }

    /**
     * Изменить график в БД
     */
    @PutMapping
    public WorkSchedule updateSchedule(@RequestBody WorkSchedule workSchedule) {
        log.info("Обновляем график работы с {} рабочими днями", workSchedule.getNumberOfWorkDays());
        return workScheduleService.updateSchedule(workSchedule);
    }

    /**
     * Удалить графики из БД
     */
    @DeleteMapping
    public void deleteSchedules() {
        log.info("Удаляем все графики из БД");
        workScheduleService.deleteSchedules();
    }

    /**
     * Удалить график в БД по id
     */
    @DeleteMapping("/{id}")
    public void deleteScheduleById(@PathVariable int id) {
        log.info("Удаляем график работы с № {}", id);
        workScheduleService.deleteScheduleById(id);
    }

    /**
     * Получить все графики в БД
     */
    @GetMapping
    public List<WorkSchedule> getSchedules() {
        log.info("Запросили все графики из БД");
        return workScheduleService.getSchedules();
    }

    /**
     * Получить график в БД по id
     */
    @DeleteMapping("/{id}")
    public WorkSchedule getScheduleById(@PathVariable int id) {
        log.info("Запросили график работы с № {}", id);
        return workScheduleService.getScheduleById(id);
    }
}
