package com.anybank.bankemployeessalaries.dao;

import com.anybank.bankemployeessalaries.model.Employee;

import java.util.List;

public interface EmployeeDao {

    /**
     * Добавление сотрудника в БД
     */
    Employee addEmployee(Employee employee);

    /**
     * Обновление сотрудника в БД
     */
    Employee updateEmployee(Employee employee);

    /**
     * Удаление всех сотрудников из БД
     */
    void deleteEmployees();

    /**
     * Удаление сотрудника по id из БД
     */
    void deleteEmployeeById(Integer id);

    /**
     * Получение списка сотрудников из БД
     */
    List<Employee> getEmployees();

    /**
     * Получение сотрудника по id
     */
    Employee findEmployeeById(Integer id);
}
