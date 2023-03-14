package com.anybank.bankemployeessalaries.service;

import com.anybank.bankemployeessalaries.dao.DepartmentDao;
import com.anybank.bankemployeessalaries.model.Department;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DepartmentService {
    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentService(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    /**
     * Добавление департамента в БД
     */
    public Department addDepartment(Department department) {
        return departmentDao.addDepartment(department);
    }

    /**
     * Обновление департамента в БД
     */
    public Department updateDepartment(Department department) {
        return departmentDao.updateDepartment(department);
    }

    /**
     * Удаление всех департаментов из БД
     */
    public void deleteDepartment() {
        departmentDao.deleteDepartment();
    }

    /**
     * Удаление департамента по id из БД
     */
    public void deleteDepartmentById(Integer id) {
        departmentDao.deleteDepartmentById(id);
    }

    /**
     * Получение списка департаментов из БД
     */
    public List<Department> getDepartment() {
        return departmentDao.getDepartment();
    }

    /**
     * Получение департамента по id
     */
    public Department findDepartmentById(Integer id) {
        return departmentDao.findDepartmentById(id);
    }
}
