package com.anybank.bankemployeessalaries.dao.impl;

import com.anybank.bankemployeessalaries.dao.DepartmentDao;
import com.anybank.bankemployeessalaries.dao.EmployeeDao;
import com.anybank.bankemployeessalaries.exception.DepartmentNotFoundException;
import com.anybank.bankemployeessalaries.model.Department;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Component
public class DepartmentDaoImpl implements DepartmentDao {
    private final JdbcTemplate jdbcTemplate;
    private final EmployeeDao employeeDao;

    @Autowired
    public DepartmentDaoImpl(JdbcTemplate jdbcTemplate, EmployeeDao employeeDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.employeeDao = employeeDao;
    }

    /**
     * Добавление департамента в БД
     */
    @Override
    public Department addDepartment(Department department) {
        String sql = "INSERT INTO department(name, head_id, phone, email, address) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, department.getName(), department.getHead().getId(), department.getPhone(),
                department.getEmail(), department.getAddress());
        SqlRowSet departmentRows = jdbcTemplate.queryForRowSet(
                "SELECT * FROM department WHERE name=?", department.getName());
        if (departmentRows.next()) {
            department.setId(departmentRows.getInt("id"));
        }

        log.info("Добавили департамент № {} в БД", department.getId());

        return department;
    }

    /**
     * Обновление департамента в БД
     */
    @Override
    public Department updateDepartment(Department department) {
        String sql = "UPDATE department" +
                " SET name=?," +
                " head_id=?," +
                " phone=?," +
                " email=?," +
                " address=?" +
                " WHERE id = ?";

        jdbcTemplate.update(sql, department.getName(), department.getHead().getId(), department.getPhone(),
                department.getEmail(), department.getAddress(), department.getId());
        log.info("Департамент {} обновлен.", department.getId());
        return department;
    }

    /**
     * Удаление всех департаментов из БД
     */
    @Override
    public void deleteDepartment() {
        String delFkEmp = "ALTER TABLE employee DROP CONSTRAINT employee_department_id_fkey";
        String sql = "DELETE FROM department";
        String addFkEmp =
                "ALTER TABLE employee " +
                        "ADD CONSTRAINT employee_department_id_fkey FOREIGN KEY (department_id) " +
                        "REFERENCES department(id)";
        jdbcTemplate.update(delFkEmp);
        jdbcTemplate.update(sql);
        jdbcTemplate.update(addFkEmp);
        log.info("Удалены все департаменты из БД");
    }

    /**
     * Удаление департамента по id из БД
     */
    @Override
    public void deleteDepartmentById(Integer id) {
        String delFkEmp = "ALTER TABLE employee DROP CONSTRAINT employee_position_id_fkey";
        String sql = "DELETE FROM department WHERE id=?";
        String addFkEmp =
                "ALTER TABLE employee " +
                        "ADD CONSTRAINT employee_position_id_fkey FOREIGN KEY (position_id) REFERENCES position(id)";
        jdbcTemplate.update(delFkEmp);
        jdbcTemplate.update(sql, id);
        jdbcTemplate.update(addFkEmp);
    }

    /**
     * Получение списка департаментов из БД
     */
    @Override
    public List<Department> getDepartment() {
        String sql = "SELECT * FROM department";
        return jdbcTemplate.query(sql, (rs, rowNum) -> makeDepartment(rs));
    }

    /**
     * Получение департамента по id
     */
    @Override
    public Department findDepartmentById(Integer id) {
        String sql = "SELECT * FROM department WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> makeDepartment(rs), id);
        } catch (Exception e) {
            throw new DepartmentNotFoundException(String.format("Департамент с id %d не найден", id));
        }
    }

//    /**
//     * Обновление руководителя отдела в департаменте
//     */
//    public Department updateHeadOfDepartment()
//    String sql = "UPDATE department\n" +
//            "    set head_id = employee.id\n" +
//            "    FROM employee\n" +
//            "    WHERE employee.position_id = (SELECT p.id\n" +
//            "    FROM employee e\n" +
//            "    LEFT JOIN position p on e.position_id = p.id\n" +
//            "    LEFT JOIN department d on p.department_id = d.id\n" +
//            "    left join grade g on p.grade_id = g.id\n" +
//            "    WHERE GRADE='Руководитель отдела')\n" +
//            "    AND\n" +
//            "    department.name = (SELECT d.name\n" +
//            "    FROM employee e\n" +
//            "    LEFT JOIN position p on e.position_id = p.id\n" +
//            "    LEFT JOIN department d on p.department_id = d.id\n" +
//            "    left join grade g on p.grade_id = g.id\n" +
//            "    WHERE GRADE='Руководитель отдела')";

    private Department makeDepartment(ResultSet rs) throws SQLException {
        Department department = Department.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .head(employeeDao.findEmployeeById(rs.getString("head_id")))
                .phone(rs.getString("phone"))
                .email(rs.getString("email"))
                .address(rs.getString("address"))
                .build();
        if (department.getName() == null) {
            department = null;
        }
        return department;
    }
}
