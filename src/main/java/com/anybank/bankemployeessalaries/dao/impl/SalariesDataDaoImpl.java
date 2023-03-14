package com.anybank.bankemployeessalaries.dao.impl;

import com.anybank.bankemployeessalaries.dao.GradeDao;
import com.anybank.bankemployeessalaries.dao.SalariesDataDao;
import com.anybank.bankemployeessalaries.model.SalariesData;
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
public class SalariesDataDaoImpl implements SalariesDataDao {
    private final JdbcTemplate jdbcTemplate;
    private GradeDao gradeDao;

    @Autowired
    public SalariesDataDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Добавить данные о зарплате в БД
     */
    @Override()
    public SalariesData addSalariesData(SalariesData salariesData) {
        String sql = "INSERT INTO salaries_data(grade_id, wage, personal_kpi, team_kpi, common_kpi) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                salariesData.getGrade().getId(),
                salariesData.getWage(),
                salariesData.getPersonalKpi(),
                salariesData.getTeamKpi(),
                salariesData.getCommonKpi());
        SqlRowSet salaryDataRow = jdbcTemplate.queryForRowSet("SELECT * FROM salaries_data " +
                "WHERE grade_id=?", salariesData.getGrade().getId());
        if (salaryDataRow.next()) {
            salariesData.setId(salaryDataRow.getInt("id"));
        }
        log.info("Добавили данные о зарплате № {}", salariesData.getId());
        return salariesData;
    }

    /**
     * Заменить данные о зарплате в БД
     */
    @Override
    public SalariesData updateSalariesData(SalariesData salariesData) {
        String sql = "UPDATE salaries_data " +
                "SET grade_id=?, " +
                "wage=?, " +
                "personal_kpi=?, " +
                "team_kpi=?, " +
                "common_kpi=?" +
                "WHERE id=?";
        jdbcTemplate.update(sql,
                salariesData.getGrade().getId(),
                salariesData.getWage(),
                salariesData.getPersonalKpi(),
                salariesData.getTeamKpi(),
                salariesData.getCommonKpi(),
                salariesData.getId()
        );

        log.info("Заменили данные о зарплате № {}", salariesData.getId());
        return salariesData;
    }

    /**
     * Удалить все данные зарплат из БД
     */
    @Override
    public void deleteSalariesData() {
        String sqlDelSal = "DELETE FROM salaries_data";
        jdbcTemplate.update(sqlDelSal);
        log.info("Удаляем все данные зарплат из БД");
    }

    /**
     * Удалить данные зарплаты в БД по id
     */
    @Override
    public void deleteSalariesDataById(Integer id) {
        String sqlDelSal = "DELETE FROM salaries_data WHERE id=?";
        jdbcTemplate.update(sqlDelSal, id);
        log.info("Удаляем данные зарплаты с № {}", id);
    }

    /**
     * Получить все данные зарплат в БД
     */
    @Override
    public List<SalariesData> getSalariesData() {
        String sql = "SELECT * FROM salaries_data";
        log.info("Запросили все данные зарплат из БД");
        return jdbcTemplate.query(sql, (rs, rowNum) -> makeSalariesData(rs));
    }

    /**
     * Получить данные зарплаты в БД по id
     */
    @Override
    public SalariesData getSalariesDataById(Integer id) {
        String sql = "SELECT * FROM salaries_data WHERE id=?";
        log.info("Запросили данные зарплаты с № {}", id);
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> makeSalariesData(rs), id);
    }

    private SalariesData makeSalariesData(ResultSet rs) throws SQLException {
        SalariesData salariesData = SalariesData.builder()
                .grade(gradeDao.getGradeById(rs.getInt("grade_id")))
                .wage(rs.getInt("wage"))
                .personalKpi(rs.getDouble("personal_kpi"))
                .teamKpi(rs.getDouble("team_kpi"))
                .commonKpi(rs.getDouble("common_kpi"))
                .build();

        if (salariesData.getWage() == 0) {
            salariesData = null;
        }
        return salariesData;
    }
}
