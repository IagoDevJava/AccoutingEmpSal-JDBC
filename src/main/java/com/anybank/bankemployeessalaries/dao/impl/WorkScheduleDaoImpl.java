package com.anybank.bankemployeessalaries.dao.impl;

import com.anybank.bankemployeessalaries.dao.WorkScheduleDao;
import com.anybank.bankemployeessalaries.exception.ScheduleNotFoundException;
import com.anybank.bankemployeessalaries.model.WorkSchedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Component
public class WorkScheduleDaoImpl implements WorkScheduleDao {
    private final JdbcTemplate jdbcTemplate;

    public WorkScheduleDaoImpl(@Lazy JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Добавить график в БД
     */
    @Override
    public WorkSchedule addSchedule(WorkSchedule workSchedule) {
        String sql = "INSERT INTO work_schedule(work_day, week_day, hours) " +
                "VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, workSchedule.getNumberOfWorkDays(), workSchedule.getNumberOfWeekDays(),
                workSchedule.getNumberOfHours());
        SqlRowSet scheduleRow = jdbcTemplate.queryForRowSet("SELECT * FROM work_schedule " +
                        "WHERE work_day=? AND week_day=?",
                workSchedule.getNumberOfWorkDays(), workSchedule.getNumberOfWeekDays());
        if (scheduleRow.next()) {
            workSchedule.setId(scheduleRow.getInt("id"));
        }
        log.info("График работы с {} рабочими днями добавили", workSchedule.getNumberOfWorkDays());
        return workSchedule;
    }

    /**
     * Заменить график в БД
     */
    @Override
    public WorkSchedule updateSchedule(WorkSchedule workSchedule) {
        String sql = "UPDATE work_schedule " +
                "SET work_day=?, " +
                "week_day=?, " +
                "hours=? " +
                "WHERE id=?";
        jdbcTemplate.update(sql, workSchedule.getNumberOfWorkDays(), workSchedule.getNumberOfWeekDays(),
                workSchedule.getNumberOfHours(), workSchedule.getId());
        log.info("График {} заменен.", workSchedule.getId());
        return workSchedule;
    }

    /**
     * Удалить графики из БД
     */
    @Override
    public void deleteSchedules() {
        String delFkEmp = "ALTER TABLE employee DROP CONSTRAINT employee_work_schedule_id_fkey";
        String sql = "DELETE FROM work_schedule";
        String addFkEmp =
                "ALTER TABLE employee " +
                        "ADD CONSTRAINT employee_work_schedule_id_fkey FOREIGN KEY (work_schedule_id) " +
                        "REFERENCES work_schedule(id)";
        jdbcTemplate.update(delFkEmp);
        jdbcTemplate.update(sql);
        jdbcTemplate.update(addFkEmp);
        log.info("Все графики из БД удалили");
    }

    /**
     * Удалить график в БД по id
     */
    @Override
    public void deleteScheduleById(int id) {
        String delFkEmp = "ALTER TABLE employee DROP CONSTRAINT employee_position_id_fkey";
        String sql = "DELETE FROM work_schedule WHERE id=?";
        String addFkEmp =
                "ALTER TABLE employee " +
                        "ADD CONSTRAINT employee_position_id_fkey FOREIGN KEY (position_id) REFERENCES position(id)";
        jdbcTemplate.update(delFkEmp);
        jdbcTemplate.update(sql, id);
        jdbcTemplate.update(addFkEmp);
        log.info("График № {} из БД удалили", id);
    }

    /**
     * Получить все графики в БД
     */
    @Override
    public List<WorkSchedule> getSchedules() {
        String sql = "SELECT * FROM work_schedule";
        log.info("Получили все графики из БД");
        return jdbcTemplate.query(sql, (rs, rowNum) -> makeSchedule(rs));
    }

    /**
     * Получить график в БД по id
     */
    @Override
    public WorkSchedule getScheduleById(int id) {
        String sql = "SELECT * FROM work_schedule WHERE id=?";
        try {
            log.info("Получили рафик № {} из БД", id);
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> makeSchedule(rs), id);
        } catch (Exception e) {
            throw new ScheduleNotFoundException("Искомого графика работы нет в БД");
        }
    }

    private WorkSchedule makeSchedule(ResultSet rs) throws SQLException {
        WorkSchedule workSchedule = WorkSchedule.builder()
                .numberOfWorkDays(rs.getInt("work_day"))
                .numberOfWeekDays(rs.getInt("week_day"))
                .numberOfHours(rs.getInt("hours"))
                .build();

        if (workSchedule.getNumberOfWorkDays() == 0) {
            workSchedule = null;
        }
        return workSchedule;
    }
}
