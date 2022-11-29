package com.anybank.bankemployeessalaries.dao;

import com.anybank.bankemployeessalaries.model.Department;

public interface DepartmentDao {
    Department findDepartmentById(int department_id);
}
