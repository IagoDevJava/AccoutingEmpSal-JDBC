package com.anybank.bankemployeessalaries.api_admin.controller;

import com.anybank.bankemployeessalaries.api_admin.service.EmployeeService;
import com.anybank.bankemployeessalaries.dto.EmployeeDto;
import com.anybank.bankemployeessalaries.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    /**
     * Добавление сотрудника в БД
     */
    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }

    /**
     * Обновление сотрудника в БД
     */
    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody Employee employee,
                                                      @PathVariable Integer employeeId) {
        return ResponseEntity.ok(employeeService.updateEmployee(employee, employeeId));
    }

    /**
     * Удаление всех сотрудников из БД
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteEmployees() {
        employeeService.deleteEmployees();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удаление сотрудника по id из БД
     */
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Integer employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Получение списка сотрудников из БД
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    /**
     * Получение сотрудника по id
     */
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }
}
