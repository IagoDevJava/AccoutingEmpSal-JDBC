package com.anybank.bankemployeessalaries.controller;

import com.anybank.bankemployeessalaries.model.Employee;
import com.anybank.bankemployeessalaries.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/employee")
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
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        log.info("Добавлем сотрудника №{} в БД", employee.getId());
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }

    /**
     * Обновление сотрудника в БД
     */
    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        log.info("Обновляем сотрудника №{} в БД", employee.getId());
        return ResponseEntity.ok(employeeService.updateEmployee(employee));
    }

    /**
     * Удаление всех сотрудников из БД
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteEmployees() {
        log.info("Очищаем БД");
        employeeService.deleteEmployees();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удаление сотрудника по id из БД
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PositiveOrZero @PathVariable Integer id) {
        log.info("Удаляем сотрудника №{} из БД", id);
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Получение списка сотрудников из БД
     */
    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        log.info("Получаем список всех сотрудников");
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    /**
     * Получение сотрудника по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PositiveOrZero @PathVariable Integer id) {
        log.info("Получаем сотрудника №{}", id);
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
}
