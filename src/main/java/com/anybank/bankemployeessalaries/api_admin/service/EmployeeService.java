package com.anybank.bankemployeessalaries.api_admin.service;

import com.anybank.bankemployeessalaries.dto.EmployeeDto;
import com.anybank.bankemployeessalaries.model.Employee;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Validated
public interface EmployeeService {

    /**
     * Добавление сотрудника в БД
     */
    EmployeeDto addEmployee(@Valid Employee employee);

    /**
     * Обновление сотрудника в БД
     */
    EmployeeDto updateEmployee(Employee employee, @PositiveOrZero Integer employeeId);

    /**
     * Удаление всех сотрудников из БД
     */
    void deleteEmployees();

    /**
     * Удаление сотрудника по id из БД
     */
    void deleteEmployeeById(@PositiveOrZero Integer employeeId);

    /**
     * Получение списка сотрудников из БД
     */
    List<EmployeeDto> getEmployees();

    /**
     * Получение сотрудника по id
     */
    EmployeeDto getEmployeeById(@PositiveOrZero Integer employeeId);
}
