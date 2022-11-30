package com.anybank.bankemployeessalaries.controller;

import com.anybank.bankemployeessalaries.model.Employee;
import com.anybank.bankemployeessalaries.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Добавление сотрудника в БД
     */
    @PostMapping
    public Employee addEmployee(@Valid @RequestBody Employee employee) {
        log.info("Добавлем сотрудника №{} в БД", employee.getId());
        return employeeService.addEmployee(employee);
    }

    /**
     * Обновление сотрудника в БД
     */
    @PutMapping
    public Employee updateEmployee(@Valid @RequestBody Employee employee) {
        log.info("Обновляем сотрудника №{} в БД", employee.getId());
        return employeeService.updateEmployee(employee);
    }

    /**
     * Удаление всех сотрудников из БД
     */
    @DeleteMapping
    public void deleteEmployees() {
        log.info("Очищаем БД");
        employeeService.deleteEmployees();
    }

    /**
     * Удаление сотрудника по id из БД
     */
    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable String id) {
        log.info("Удаляем сотрудника №{} из БД", id);
        employeeService.deleteEmployeeById(id);
    }

    /**
     * Получение списка сотрудников из БД
     */
    @GetMapping
    public List<Employee> getEmployees() {
        log.info("Получаем список всех сотрудников");
        return employeeService.getEmployees();
    }

    /**
     * Получение сотрудника по id
     */
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        log.info("Получаем сотрудника №{}", id);
        return employeeService.getEmployeeById(id);
    }
}
