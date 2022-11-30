package com.anybank.bankemployeessalaries.controller;

import com.anybank.bankemployeessalaries.model.Department;
import com.anybank.bankemployeessalaries.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Добавление департамента в БД
     */
    @PostMapping
    public Department addDepartment(@Valid @RequestBody Department department) {
        log.info("Добавляем департамент № {} в БД", department.getId());
        return departmentService.addDepartment(department);
    }

    /**
     * Обновление департамента в БД
     */
    @PutMapping
    public Department updateDepartment(@Valid @RequestBody Department department) {
        log.info("Обновляем департамент № {} в БД", department.getId());
        return departmentService.updateDepartment(department);
    }

    /**
     * Удаление всех департаментов из БД
     */
    @DeleteMapping
    public void deleteDepartment() {
        log.info("Очищаем департаменты БД");
        departmentService.deleteDepartment();
    }

    /**
     * Удаление департамента по id из БД
     */
    @DeleteMapping("/{id}")
    public void deleteDepartmentById(String id) {
        log.info("Удаляем департамент № {} в БД", id);
        departmentService.deleteDepartmentById(id);
    }

    /**
     * Получение списка департаментов из БД
     */
    @GetMapping
    public List<Department> getDepartment() {
        log.info("Получаем список департаментов БД");
        return departmentService.getDepartment();
    }

    /**
     * Получение департамента по id
     */
    @GetMapping("/{id}")
    public Department findDepartmentById(String id) {
        log.info("Получаем департамент № {} в БД", id);
        return departmentService.findDepartmentById(id);
    }
}
