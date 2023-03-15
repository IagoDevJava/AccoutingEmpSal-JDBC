package com.anybank.bankemployeessalaries.controller;

import com.anybank.bankemployeessalaries.model.SalariesData;
import com.anybank.bankemployeessalaries.service.SalariesDataService;
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
@RequestMapping("/salaries-data")
public class SalariesDataController {
    private final SalariesDataService salariesDataService;

    @Autowired
    public SalariesDataController(SalariesDataService salariesDataService) {
        this.salariesDataService = salariesDataService;
    }

    /**
     * Добавить данные о зарплате в БД
     */
    @PostMapping()
    public ResponseEntity<SalariesData> addSalariesData(@Valid @RequestBody SalariesData salariesData) {
        log.info("Добавляем данные о зарплате № {}", salariesData.getId());
        return ResponseEntity.ok(salariesDataService.addSalariesData(salariesData));
    }

    /**
     * Заменить данные о зарплате в БД
     */
    @PatchMapping
    public ResponseEntity<SalariesData> updateSalariesData(@RequestBody SalariesData salariesData) {
        log.info("Заменяем данные о зарплате № {}", salariesData.getId());
        return ResponseEntity.ok(salariesDataService.updateSalariesData(salariesData));
    }

    /**
     * Обновить значения KPI
     */
    @PatchMapping
    public ResponseEntity<SalariesData> updateKpiSalariesData(@RequestBody SalariesData salariesData) {
        return ResponseEntity.ok(salariesDataService.updateKpiSalariesData(salariesData));
    }

    /**
     * Удалить все данные зарплат из БД
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteSalariesData() {
        log.info("Удаляем все данные зарплат из БД");
        salariesDataService.deleteSalariesData();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удалить данные зарплаты в БД по id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalariesDataById(@PositiveOrZero @PathVariable Integer id) {
        log.info("Удаляем данные зарплаты с № {}", id);
        salariesDataService.deleteSalariesDataById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Получить все данные зарплат в БД
     */
    @GetMapping
    public ResponseEntity<List<SalariesData>> getSalariesData() {
        log.info("Запросили все данные зарплат из БД");
        return ResponseEntity.ok(salariesDataService.getSalariesData());
    }

    /**
     * Получить данные зарплаты в БД по id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<SalariesData> getSalariesDataById(@PositiveOrZero @PathVariable Integer id) {
        log.info("Запросили данные зарплаты с № {}", id);
        return ResponseEntity.ok(salariesDataService.getSalariesDataById(id));
    }
}
