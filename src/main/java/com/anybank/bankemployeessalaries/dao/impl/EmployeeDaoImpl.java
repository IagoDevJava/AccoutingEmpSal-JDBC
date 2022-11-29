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
        jdbcTemplate.update(sql, employee.getSurname(), employee.getFirstname(), employee.getLastname(),
                employee.getGender(), employee.getDepartment().getId(), employee.getPhone(), employee.getEmail(),
                employee.getPosition().getId(), employee.getWorkSchedule().getId(), employee.getDateOfAdmission());
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
     * Обновление сотрудника в БД
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
                employee.getSurname(), employee.getFirstname(), employee.getLastname(), employee.getGender(),
                employee.getDepartment().getId(), employee.getPhone(), employee.getEmail(),
                employee.getPosition().getId(), employee.getWorkSchedule().getId(),
                employee.getDateOfAdmission(), employee.getDateOfDismissal(), employee.getId());
        log.info("Сотрудник {} обновлен.", employee.getId());
        return employee;
    }

    /**
     * Удаление всех сотрудников из БД
     */
    @Override
    public void deleteEmployees() {
        String sqlGr = "DELETE FROM grade";
        String sqlDep = "DELETE FROM department";
        String sqlP = "DELETE FROM position";
        String sqlW = "DELETE FROM work_schedule";
        String sqlEmp = "DELETE FROM employee";
        jdbcTemplate.update(sqlGr);
        jdbcTemplate.update(sqlDep);
        jdbcTemplate.update(sqlP);
        jdbcTemplate.update(sqlW);
        jdbcTemplate.update(sqlEmp);
        log.info("Удалены все сотрудники из БД");
    }

    /**
     * Удаление сотрудника по id из БД
     */
    @Override
    public void deleteEmployeeById(String id) {
        String sqlDep = "DELETE FROM department WHERE head_id=?";
        String sqlEmp = "DELETE FROM employee WHERE id=?";
        jdbcTemplate.update(sqlDep, id);
        jdbcTemplate.update(sqlEmp, id);
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
    public Employee getEmployeeById(String id) {
        String sql = "SELECT * FROM employee WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> makeEmployee(rs), id);
        } catch (Exception e) {
            throw new EmployeeNotFoundException(String.format("Сотрудник с id %d не найден", Integer.parseInt(id)));
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
                .workSchedule(workscheduleDao.findWorkScheduleById(rs.getInt("work_schedule_id")))
                .dateOfAdmission(LocalDate.parse(rs.getString("date_of_admission")))
                .dateOfDismissal(LocalDate.parse(rs.getString("date_of_dismissal")))
                .build();

        if (employee.getSurname() == null) {
            employee = null;
        }

        return employee;
    }
}
