package com.anybank.bankemployeessalaries.service;

import com.anybank.bankemployeessalaries.dao.EmployeeDao;
import com.anybank.bankemployeessalaries.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployeeService {
    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /**
     * Добавление сотрудника в БД
     */
    public Employee addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    /**
     * Обновление сотрудника в БД
     */
    public Employee updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    /**
     * Удаление всех сотрудников из БД
     */
    public void deleteEmployees() {
        employeeDao.deleteEmployees();
    }

    /**
     * Удаление сотрудника по id из БД
     */
    public void deleteEmployeeById(String id) {
        employeeDao.deleteEmployeeById(id);
    }

    /**
     * Получение списка сотрудников из БД
     */
    public List<Employee> getEmployees() {
        return employeeDao.getEmployees();
    }

    /**
     * Получение сотрудника по id
     */
    public Employee getEmployeeById(String id) {
        return employeeDao.findEmployeeById(id);
    }
}
