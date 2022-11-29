package com.anybank.bankemployeessalaries.dao.impl;

import com.anybank.bankemployeessalaries.dao.DepartmentDao;
import com.anybank.bankemployeessalaries.model.Department;

public class DepartmentDaoImpl implements DepartmentDao {




//    --Обновление руководителя отдела в департаменте
    String sql = "UPDATE department\n" +
        "    set head_id = employee.id\n" +
        "    FROM employee\n" +
        "    WHERE employee.position_id = (SELECT p.id\n" +
        "    FROM employee e\n" +
        "    LEFT JOIN position p on e.position_id = p.id\n" +
        "    LEFT JOIN department d on p.department_id = d.id\n" +
        "    left join grade g on p.grade_id = g.id\n" +
        "    WHERE GRADE='Руководитель отдела')\n" +
        "    AND\n" +
        "    department.name = (SELECT d.name\n" +
        "    FROM employee e\n" +
        "    LEFT JOIN position p on e.position_id = p.id\n" +
        "    LEFT JOIN department d on p.department_id = d.id\n" +
        "    left join grade g on p.grade_id = g.id\n" +
        "    WHERE GRADE='Руководитель отдела')";


    @Override
    public Department findDepartmentById(int department_id) {
        return ;
    }
}
