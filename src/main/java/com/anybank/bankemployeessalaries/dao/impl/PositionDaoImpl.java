package com.anybank.bankemployeessalaries.dao.impl;

import com.anybank.bankemployeessalaries.dao.DepartmentDao;
import com.anybank.bankemployeessalaries.dao.GradeDao;
import com.anybank.bankemployeessalaries.dao.PositionDao;
import com.anybank.bankemployeessalaries.exception.PositionNotFoundException;
import com.anybank.bankemployeessalaries.model.Position;
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
public class PositionDaoImpl implements PositionDao {
    private final JdbcTemplate jdbcTemplate;
    private DepartmentDao departmentDao;
    private GradeDao gradeDao;

    @Autowired
    public PositionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Добавление должности в БД
     */
    @Override
    public Position addPosition(Position position) {
        String sql = "INSERT INTO position(post, department_id, grade_id) " +
                "VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, position.getPost(),
                position.getDepartment().getId(), position.getGrade().getId());
        SqlRowSet positionRows = jdbcTemplate.queryForRowSet(
                "SELECT * FROM position WHERE post=?", position.getPost());
        if (positionRows.next()) {
            position.setId(positionRows.getInt("id"));
        }

        log.info("Добавили должность № {} в БД", position.getId());

        return position;
    }

    /**
     * Обновление должности в БД
     */
    @Override
    public Position updatePosition(Position position) {
        String sql = "UPDATE position" +
                " SET post=?," +
                " department_id=?," +
                " grade_id=?" +
                " WHERE id = ?";

        jdbcTemplate.update(sql, position.getPost(), position.getPost(),
                position.getDepartment().getId(), position.getGrade().getId(), position.getId());
        log.info("Должность {} обновлена.", position.getId());
        return position;
    }

    /**
     * Удаление всех должностей из БД
     */
    @Override
    public void deletePositions() {
        String sql = "DELETE FROM position";
        jdbcTemplate.update(sql);
    }

    /**
     * Удаление должности по id из БД
     */
    @Override
    public void deletePositionById(String id) {
        String sql = "DELETE FROM position WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * Получение списка должностей из БД
     */
    @Override
    public List<Position> getPosition() {
        String sql = "SELECT * FROM position";
        return jdbcTemplate.query(sql, (rs, rowNum) -> makePosition(rs));
    }

    /**
     * Получение должности по id
     */
    @Override
    public Position findPositionById(String id) {
        String sql = "SELECT * FROM position WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> makePosition(rs), id);
        } catch (Exception e) {
            throw new PositionNotFoundException(String.format("Должность с id %d не найден", Integer.parseInt(id)));
        }
    }

    private Position makePosition(ResultSet rs) throws SQLException {
        Position position = Position.builder()
                .id(rs.getInt("id"))
                .post(rs.getString("post"))
                .department(departmentDao.findDepartmentById(rs.getString("department_id")))
                .grade(gradeDao.findGradeById(rs.getString("department_id")))
                .build();
        if (position.getPost() == null) {
            position = null;
        }
        return position;
    }
}
