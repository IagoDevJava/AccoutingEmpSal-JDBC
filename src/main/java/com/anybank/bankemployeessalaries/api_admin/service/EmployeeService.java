package com.anybank.bankemployeessalaries.api_admin.service;

import com.anybank.bankemployeessalaries.dto.EmployeeDto;
import com.anybank.bankemployeessalaries.model.Employee;

import java.util.List;

public interface EmployeeService {

    /**
     * Добавление сотрудника в БД
     */
    EmployeeDto addEmployee(Employee employee);

    /**
     * Обновление сотрудника в БД
     */
    EmployeeDto updateEmployee(Employee employee, Integer employeeId);

    /**
     * Удаление всех сотрудников из БД
     */
    void deleteEmployees();

    /**
     * Удаление сотрудника по id из БД
     */
    void deleteEmployeeById(Integer employeeId);

    /**
     * Получение списка сотрудников из БД
     */
    List<EmployeeDto> getEmployees();

    /**
     * Получение сотрудника по id
     */
    EmployeeDto getEmployeeById(Integer employeeId);
}
