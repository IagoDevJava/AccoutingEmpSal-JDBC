package com.anybank.bankemployeessalaries.dao.impl;

import com.anybank.bankemployeessalaries.dao.DepartmentDao;
import com.anybank.bankemployeessalaries.dao.EmployeeDao;
import com.anybank.bankemployeessalaries.dao.PositionDao;
import com.anybank.bankemployeessalaries.dao.WorkScheduleDao;
import com.anybank.bankemployeessalaries.exception.EmployeeNotFoundException;
import com.anybank.bankemployeessalaries.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class EmployeeDaoImpl implements EmployeeDao {
    private final JdbcTemplate jdbcTemplate;
    private DepartmentDao departmentDao;
    private WorkScheduleDao workscheduleDao;
    private PositionDao positionDao;

    @Autowired
    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Добавление сотрудника в БД
     */
    @Override
    public Employee addEmployee(Employee employee) {
        String sql = "INSERT INTO employee(surname, firstname, lastname, gender, department_id, phone, email," +
                "position_id, work_schedule_id, date_of_admission) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                employee.getSurname(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getGender(),
                employee.getDepartment().getId(),
                employee.getPhone(),
                employee.getEmail(),
                employee.getPosition().getId(),
                employee.getWorkSchedule().getId(),
                employee.getDateOfAdmission());
        SqlRowSet employeeRows = jdbcTemplate.queryForRowSet(
                "SELECT * FROM employee WHERE surname=? AND firstname=? AND lastname=?",
                employee.getSurname(), employee.getFirstname(), employee.getLastname());
        if (employeeRows.next()) {
            employee.setId(employeeRows.getInt("id"));
        }
        log.info("Добавили сотрудника № {} в БД", employee.getId());
        return employee;
    }

    /**
     * Замена сотрудника в БД
     */
    @Override
    public Employee updateEmployee(Employee employee) {
        String sql = "UPDATE employee" +
                " SET surname=?," +
                " firstname=?," +
                " lastname=?," +
                " gender=?," +
                " department_id=?," +
                " phone=?," +
                " email=?," +
                " position_id=?," +
                " work_schedule_id=?," +
                " date_of_admission=?," +
                " date_of_dismissal=?" +
                " WHERE id = ?";

        jdbcTemplate.update(sql,
                employee.getSurname(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getGender(),
                employee.getDepartment().getId(),
                employee.getPhone(),
                employee.getEmail(),
                employee.getPosition().getId(),
                employee.getWorkSchedule().getId(),
                employee.getDateOfAdmission(),
                employee.getDateOfDismissal(),
                employee.getId());
        log.info("Сотрудник {} обновлен.", employee.getId());
        return employee;
    }

    /**
     * Удаление всех сотрудников из БД
     */
    @Override
    public void deleteEmployees() {
        String delFkDep = "ALTER TABLE department DROP CONSTRAINT fk_dephead_to_employee";
        String delFkAtt = "ALTER TABLE attendance_data DROP CONSTRAINT attendance_data_employee_id_fkey";
        String sql = "DELETE FROM employee";
        String addFkDep =
                "ALTER TABLE department " +
                        "ADD CONSTRAINT fk_dephead_to_employee FOREIGN KEY (head_id) REFERENCES employee(id)";
        String addFkAtt = "ALTER TABLE attendance_data ADD CONSTRAINT attendance_data_employee_id_fkey " +
                "FOREIGN KEY (employee_id) REFERENCES employee(id)";
        jdbcTemplate.update(delFkDep);
        jdbcTemplate.update(delFkAtt);
        jdbcTemplate.update(sql);
        jdbcTemplate.update(addFkDep);
        jdbcTemplate.update(addFkAtt);
        log.info("Удалены все сотрудники из БД");
    }

    /**
     * Удаление сотрудника по id из БД
     */
    @Override
    public void deleteEmployeeById(Integer id) {
        String delFkDep = "ALTER TABLE department DROP CONSTRAINT fk_dephead_to_employee";
        String delFkAtt = "ALTER TABLE attendance_data DROP CONSTRAINT attendance_data_employee_id_fkey";
        String sql = "DELETE FROM employee WHERE id=?";
        String addFkDep =
                "ALTER TABLE department " +
                        "ADD CONSTRAINT fk_dephead_to_employee FOREIGN KEY (head_id) REFERENCES employee(id)";
        String addFkAtt = "ALTER TABLE attendance_data ADD CONSTRAINT attendance_data_employee_id_fkey " +
                "FOREIGN KEY (employee_id) REFERENCES employee(id)";
        jdbcTemplate.update(delFkDep);
        jdbcTemplate.update(delFkAtt);
        jdbcTemplate.update(sql, id);
        jdbcTemplate.update(addFkDep);
        jdbcTemplate.update(addFkAtt);
    }

    /**
     * Получение списка сотрудников из БД
     */
    @Override
    public List<Employee> getEmployees() {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.query(sql, (rs, rowNum) -> makeEmployee(rs));
    }

    /**
     * Получение сотрудника по id
     */
    @Override
    public Employee findEmployeeById(Integer id) {
        String sql = "SELECT * FROM employee WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> makeEmployee(rs), id);
        } catch (Exception e) {
            throw new EmployeeNotFoundException(String.format("Сотрудник с id %d не найден", id));
        }
    }

    private Employee makeEmployee(ResultSet rs) throws SQLException {
        Employee employee = Employee.builder()
                .id(rs.getInt("id"))
                .surname(rs.getString("surname"))
                .firstname(rs.getString("firstname"))
                .lastname(rs.getString("lastname"))
                .gender(rs.getString(""))
                .department(departmentDao.findDepartmentById(rs.getInt("department_id")))
                .phone(rs.getString("phone"))
                .email(rs.getString("email"))
                .position(positionDao.findPositionById(rs.getInt("position_id")))
                .workSchedule(workscheduleDao.getScheduleById(rs.getInt("work_schedule_id")))
                .dateOfAdmission(LocalDate.parse(rs.getString("date_of_admission")))
                .dateOfDismissal(LocalDate.parse(rs.getString("date_of_dismissal")))
                .build();

        if (employee.getSurname() == null) {
            employee = null;
        }

        return employee;
    }
}
