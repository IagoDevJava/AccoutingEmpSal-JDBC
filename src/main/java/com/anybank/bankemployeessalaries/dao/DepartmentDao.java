package com.anybank.bankemployeessalaries.dao;

import com.anybank.bankemployeessalaries.model.Department;

import java.util.List;

public interface DepartmentDao {

    /**
     * Добавление департамента в БД
     */
    Department addDepartment(Department department);

    /**
     * Обновление департамента в БД
     */
    Department updateDepartment(Department department);

    /**
     * Удаление всех департаментов из БД
     */
    void deleteDepartment();

    /**
     * Удаление департамента по id из БД
     */
    void deleteDepartmentById(Integer id);

    /**
     * Получение списка департаментов из БД
     */
    List<Department> getDepartment();

    /**
     * Получение департамента по id
     */
    Department findDepartmentById(Integer id);
}
