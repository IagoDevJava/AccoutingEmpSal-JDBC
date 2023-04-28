package com.anybank.bankemployeessalaries.dao.impl;

import com.anybank.bankemployeessalaries.dao.GradeDao;
import com.anybank.bankemployeessalaries.exception.GradeNotFoundException;
import com.anybank.bankemployeessalaries.model.Grade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Component
public class GradeDaoImpl implements GradeDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GradeDaoImpl(@Lazy JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Добавление грейда в БД
     */
    @Override
    public Grade addGrade(Grade grade) {
        String sql = "INSERT INTO grade(grade) " +
                "VALUES (?)";
        jdbcTemplate.update(sql, grade.getName());
        SqlRowSet gradeRows = jdbcTemplate.queryForRowSet(
                "SELECT * FROM grade WHERE grade=?", grade.getName());
        if (gradeRows.next()) {
            grade.setId(gradeRows.getInt("id"));
        }

        log.info("Добавили грейд № {} в БД", grade.getId());

        return grade;
    }

    /**
     * Обновление грейда в БД
     */
    @Override
    public Grade updateGrade(Grade grade) {
        String sql = "UPDATE grade" +
                " SET grade=?" +
                " WHERE id = ?";

        jdbcTemplate.update(sql, grade.getName(), grade.getId());
        log.info("Грейд {} обновлен.", grade.getId());
        return grade;
    }

    /**
     * Удаление всех грейдов из БД
     */
    @Override
    public void deleteGrades() {
        String delFKSalary = "ALTER TABLE salaries_data DROP CONSTRAINT salaries_data_grade_id_fkey";
        String delFKPosition = "ALTER TABLE position DROP CONSTRAINT position_grade_id_fkey";
        String sql = "DELETE FROM grade";
        String addFkSalary =
                "ALTER TABLE salaries_data " +
                        "ADD CONSTRAINT salaries_data_grade_id_fkey FOREIGN KEY (grade_id) REFERENCES grade(id)";
        String addFkPosition =
                "ALTER TABLE position " +
                        "ADD CONSTRAINT position_grade_id_fkey FOREIGN KEY (grade_id) REFERENCES grade(id)";
        jdbcTemplate.update(delFKPosition);
        jdbcTemplate.update(delFKSalary);
        jdbcTemplate.update(sql);
        jdbcTemplate.update(addFkSalary);
        jdbcTemplate.update(addFkPosition);
    }

    /**
     * Удаление грейда по id из БД
     */
    @Override
    public void deleteGradeById(Integer id) {
        String delFKSalary = "ALTER TABLE salaries_data DROP CONSTRAINT salaries_data_grade_id_fkey";
        String delFKPosition = "ALTER TABLE position DROP CONSTRAINT position_grade_id_fkey";
        String sql = "DELETE FROM grade WHERE id=?";
        String addFkSalary =
                "ALTER TABLE salaries_data " +
                        "ADD CONSTRAINT salaries_data_grade_id_fkey FOREIGN KEY (grade_id) REFERENCES grade(id)";
        String addFkPosition =
                "ALTER TABLE position " +
                        "ADD CONSTRAINT position_grade_id_fkey FOREIGN KEY (grade_id) REFERENCES grade(id)";
        jdbcTemplate.update(delFKPosition);
        jdbcTemplate.update(delFKSalary);
        jdbcTemplate.update(sql, id);
        jdbcTemplate.update(addFkSalary);
        jdbcTemplate.update(addFkPosition);
    }

    /**
     * Получение списка грейдов из БД
     */
    @Override
    public List<Grade> getGrades() {
        String sql = "SELECT * FROM grade";
        return jdbcTemplate.query(sql, (rs, rowNum) -> makeGrade(rs));
    }

    /**
     * Получение грейда по id
     */
    @Override
    public Grade getGradeById(Integer id) {
        String sql = "SELECT * FROM grade WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> makeGrade(rs), id);
        } catch (Exception e) {
            throw new GradeNotFoundException(String.format("Грейд с id %d не найден", id));
        }
    }

    private Grade makeGrade(ResultSet rs) throws SQLException {
        Grade grade = Grade.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("grade"))
                .build();
        if (grade.getName() == null) {
            grade = null;
        }
        return grade;
    }
}
