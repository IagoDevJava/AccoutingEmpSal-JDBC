package com.anybank.bankemployeessalaries.controller;

import com.anybank.bankemployeessalaries.model.SalariesData;
import com.anybank.bankemployeessalaries.service.SalariesDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
    public SalariesData addSalariesData(@RequestBody SalariesData salariesData) {
        log.info("Добавляем данные о зарплате № {}", salariesData.getId());
        return salariesDataService.addSalariesData(salariesData);
    }

    /**
     * Заменить данные о зарплате в БД
     */
    @PutMapping
    public SalariesData updateSalariesData(@RequestBody SalariesData salariesData) {
        log.info("Заменяем данные о зарплате № {}", salariesData.getId());
        return salariesDataService.updateSalariesData(salariesData);
    }

    /**
     * Удалить все данные зарплат из БД
     */
    @DeleteMapping
    public void deleteSalariesData() {
        log.info("Удаляем все данные зарплат из БД");
        salariesDataService.deleteSalariesData();
    }

    /**
     * Удалить данные зарплаты в БД по id
     */
    @DeleteMapping("/{id}")
    public void deleteSalariesDataById(@PathVariable int id) {
        log.info("Удаляем данные зарплаты с № {}", id);
        salariesDataService.deleteSalariesDataById(id);
    }

    /**
     * Получить все данные зарплат в БД
     */
    @GetMapping
    public List<SalariesData> getSalariesData() {
        log.info("Запросили все данные зарплат из БД");
        return salariesDataService.getSalariesData();
    }

    /**
     * Получить данные зарплаты в БД по id
     */
    @DeleteMapping("/{id}")
    public SalariesData getSalariesDataById(@PathVariable int id) {
        log.info("Запросили данные зарплаты с № {}", id);
        return salariesDataService.getSalariesDataById(id);
    }
}
