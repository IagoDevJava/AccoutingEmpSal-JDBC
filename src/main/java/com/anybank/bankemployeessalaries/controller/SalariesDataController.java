package com.anybank.bankemployeessalaries.controller;

import com.anybank.bankemployeessalaries.dto.SalariesDataDto;
import com.anybank.bankemployeessalaries.model.SalariesData;
import com.anybank.bankemployeessalaries.service.SalariesDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RestController
@AllArgsConstructor
@RequestMapping("/salaries-dates")
public class SalariesDataController {
    private final SalariesDataService salariesDataService;

    /**
     * Добавить данные о зарплате в БД
     */
    @Transactional
    @PostMapping()
    public ResponseEntity<SalariesDataDto> addSalariesData(@Valid @RequestBody SalariesData salariesData) {
        log.info("Добавляем данные о зарплате № {}", salariesData.getId());
        return ResponseEntity.ok(salariesDataService.addSalariesData(salariesData));
    }

    /**
     * Заменить данные о зарплате в БД
     */
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<SalariesDataDto> updateSalariesData(@RequestBody SalariesData salariesData,
                                                              @PathVariable @PositiveOrZero Integer id) {
        log.info("Заменяем данные о зарплате № {}", salariesData.getId());
        return ResponseEntity.ok(salariesDataService.updateSalariesData(salariesData, id));
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
    public ResponseEntity<List<SalariesDataDto>> getSalariesData() {
        log.info("Запросили все данные зарплат из БД");
        return ResponseEntity.ok(salariesDataService.getSalariesData());
    }

    /**
     * Получить данные зарплаты в БД по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<SalariesDataDto> getSalariesDataById(@PositiveOrZero @PathVariable Integer id) {
        log.info("Запросили данные зарплаты с № {}", id);
        return ResponseEntity.ok(salariesDataService.getSalariesDataById(id));
    }
}
