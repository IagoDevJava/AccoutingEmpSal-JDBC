package com.anybank.bankemployeessalaries.api_admin.controller;

import com.anybank.bankemployeessalaries.api_admin.service.WorkScheduleService;
import com.anybank.bankemployeessalaries.dto.WorkScheduleDto;
import com.anybank.bankemployeessalaries.model.WorkSchedule;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/admin/work-schedule")
public class WorkScheduleController {
    private final WorkScheduleService workScheduleService;

    /**
     * Добавить график в БД
     */
    @PostMapping()
    public ResponseEntity<WorkScheduleDto> addSchedule(@Valid @RequestBody WorkSchedule workSchedule) {
        return ResponseEntity.ok(workScheduleService.addSchedule(workSchedule));
    }

    /**
     * Изменить график в БД
     */
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<WorkScheduleDto> updateSchedule(@RequestBody WorkSchedule workSchedule,
                                                          @PositiveOrZero @PathVariable Integer scheduleId) {
        return ResponseEntity.ok(workScheduleService.updateSchedule(workSchedule, scheduleId));
    }

    /**
     * Удалить графики из БД
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteSchedules() {
        workScheduleService.deleteSchedules();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удалить график в БД по id
     */
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteScheduleById(@PositiveOrZero @PathVariable Integer scheduleId) {
        workScheduleService.deleteScheduleById(scheduleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Получить все графики в БД
     */
    @GetMapping
    public ResponseEntity<List<WorkScheduleDto>> getSchedules() {
        return ResponseEntity.ok(workScheduleService.getSchedules());
    }

    /**
     * Получить график в БД по id
     */
    @GetMapping("/{scheduleId}")
    public ResponseEntity<WorkScheduleDto> getScheduleById(@PositiveOrZero @PathVariable Integer scheduleId) {
        return ResponseEntity.ok(workScheduleService.getScheduleById(scheduleId));
    }
}
