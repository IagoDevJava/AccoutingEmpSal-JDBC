//package com.anybank.bankemployeessalaries.dao.impl;
//
//import com.anybank.bankemployeessalaries.dao.SalaryDao;
//import com.anybank.bankemployeessalaries.model.Salary;
//import com.anybank.bankemployeessalaries.enum_model.JobStatus;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.support.rowset.SqlRowSet;
//import org.springframework.stereotype.Component;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//@Slf4j
//@Component
//public class SalaryDaoImpl implements SalaryDao {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public SalaryDaoImpl(@Lazy JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    /**
//     * Рассчитать данные о зарплате по сотруднику за месяц
//     * Оклад, бонус, отработанные дни, дни в месяце, kpi
//     */
//    @Override
//    public Salary calculateSalaryByMonthForEmployee(Integer employeeId, Integer countDays, String end) {
//        String sqlWage = "SELECT wage FROM salaries_data " +
//                "LEFT JOIN position p on salaries_data.grade_id = p.grade_id " +
//                "LEFT JOIN employee e on p.id = e.position_id WHERE e.id=?";
//        Integer wage = jdbcTemplate.queryForObject(sqlWage, (rs, rowNum) -> makeWage(rs), employeeId);
//        if (wage == null) {
//            wage = 0;
//        }
//
//        String sqlBonus = "SELECT bonus FROM salaries_data " +
//                "LEFT JOIN position p on salaries_data.grade_id = p.grade_id " +
//                "LEFT JOIN employee e on p.id = e.position_id WHERE e.id=?";
//        Integer bonus = jdbcTemplate.queryForObject(sqlBonus, (rs, rowNum) -> makeBonus(rs), employeeId);
//        if (bonus == null) {
//            bonus = 0;
//        }
//
//        String sqlCountDayAttendance =
//                "SELECT count(id) FROM attendance_data WHERE id=? AND status=? AND date_att BETWEEN ? AND ?";
//        String start = makeFirstDayDate(end);
//        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.BASIC_ISO_DATE);
//        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.BASIC_ISO_DATE);
//        Integer countDayAttendance = jdbcTemplate.queryForObject(
//                sqlCountDayAttendance,
//                (rs, rowNum) -> makeCountDayAttendance(rs),
//                employeeId,
//                JobStatus.WORKING.toString().toLowerCase(),
//                startDate,
//                endDate.minusDays(1)
//        );
//        if (countDayAttendance == null) {
//            countDayAttendance = 0;
//        }
//
//        String sqlKpi = "SELECT personal_kpi, team_kpi, common_kpi FROM salaries_data " +
//                "LEFT JOIN position p on salaries_data.grade_id = p.grade_id " +
//                "LEFT JOIN employee e on p.id = e.position_id WHERE e.id=?";
//        Double personalKpi = jdbcTemplate.queryForObject(sqlKpi, (rs, rowNum) -> makePersonalKpi(rs), employeeId);
//        if (personalKpi == null) {
//            personalKpi = 0.0;
//        }
//        Double teamKpi = jdbcTemplate.queryForObject(sqlKpi, (rs, rowNum) -> makeTeamKpi(rs), employeeId);
//        if (teamKpi == null) {
//            teamKpi = 0.0;
//        }
//        Double commonKpi = jdbcTemplate.queryForObject(sqlKpi, (rs, rowNum) -> makeCommonKpi(rs), employeeId);
//        if (commonKpi == null) {
//            commonKpi = 0.0;
//        }
//
//        int salaryWithoutBonus = wage / countDays * countDayAttendance;
//        double totalBonus = bonus * personalKpi + bonus * teamKpi + bonus * commonKpi;
//        double totalSalary = salaryWithoutBonus + totalBonus;
//
//        return saveSalaryByPeriodForEmployee(totalSalary, employeeId, startDate, endDate);
//    }
//
//    /**
//     * получить данные о зарплате по сотруднику за месяц
//     */
//    @Override
//    public Salary getSalaryByMonthForEmployee(Integer employeeId, String month, String year) {
//
//        String sql = "SELECT * FROM salary WHERE employee_id=? AND month=? AND year=?";
//        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> makeSalaries(rs), employeeId, month, year);
//    }
//
//    /**
//     * получить данные о зарплате по сотруднику за год
//     */
//    @Override
//    public List<Salary> getSalaryByYearForEmployee(Integer employeeId, String year) {
//        String sql = "SELECT * FROM salary WHERE employee_id=? AND year=?";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> makeSalaries(rs), employeeId, year);
//    }
//
//    /**
//     * получить данные о зарплате по отделу за месяц
//     */
//    @Override
//    public List<Salary> getSalaryByMonthForDepartment(Integer departmentId, String month, String year) {
//        String sql = "SELECT * FROM salary WHERE department_id=? AND month=? AND year=?";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> makeSalaries(rs), departmentId, month, year);
//    }
//
//    /**
//     * получить данные о зарплате по отделу за год
//     */
//    @Override
//    public List<Salary> getSalaryByYearForDepartment(Integer departmentId, String year) {
//        String sql = "SELECT * FROM salary WHERE department_id=? AND year=?";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> makeSalaries(rs), departmentId, year);
//    }
//
//    /**
//     * получить данные о зарплате по компании за месяц
//     */
//    @Override
//    public List<Salary> getSalaryByMonth(String month, String year) {
//        String sql = "SELECT * FROM salary WHERE month=? AND year=?";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> makeSalaries(rs), month, year);
//    }
//
//    /**
//     * получить данные о зарплате по компании за год
//     */
//    @Override
//    public List<Salary> getSalaryByYear(String year) {
//        String sql = "SELECT * FROM salary WHERE year=?";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> makeSalaries(rs), year);
//    }
//
//    // создать salary
//    private Salary makeSalaries(ResultSet rs) throws SQLException {
//        Salary salary = Salary.builder()
//                .id(rs.getLong("id"))
//                .departmentId(rs.getInt("department_id"))
//                .employeeId(rs.getInt("employee_id"))
//                .month(rs.getString("month"))
//                .year(rs.getString("year"))
//                .build();
//
//        if (salary.getEmployeeId() == 0 && salary.getMonth() == null && salary.getYear() == null) {
//            salary = null;
//        }
//        return salary;
//    }
//
//    //Сохранить данные о зарплате по сотруднику за месяц
//    private Salary saveSalaryByPeriodForEmployee(
//            Double salaryNumber, Integer employeeId, LocalDate startDate, LocalDate endDate
//    ) {
//        String sqlDepartmentId = "SELECT department_id FROM employee WHERE id=?";
//        Integer departmentId = jdbcTemplate.queryForObject(
//                sqlDepartmentId,
//                (rs, rowNum) -> makeDepartmentId(rs),
//                employeeId
//        );
//        if (departmentId == null) {
//            departmentId = 0;
//        }
//
//        String sqlMonth =
//                "SELECT EXTRACT(MONTH FROM CAST(date_att AS date)) " +
//                        "FROM attendance_data WHERE date_att BETWEEN ? AND ?";
//        Integer numberOfMonth = jdbcTemplate.queryForObject(
//                sqlMonth,
//                (rs, rowNum) -> makeMonth(rs),
//                startDate,
//                endDate.minusDays(1)
//        );
//        String sqlYear =
//                "SELECT EXTRACT(YEAR FROM CAST(date_att AS date)) " +
//                        "FROM attendance_data WHERE date_att BETWEEN ? AND ?";
//        Integer numberOfYear = jdbcTemplate.queryForObject(
//                sqlYear,
//                (rs, rowNum) -> makeYear(rs),
//                startDate,
//                endDate.minusDays(1)
//        );
//
//        String sql = "INSERT INTO salary(department_id, employee_id, month, year, salary) " +
//                "VALUES (?, ?, ?, ?, ?)";
//        jdbcTemplate.update(sql, departmentId, employeeId, numberOfMonth, numberOfYear, salaryNumber);
//        Salary salary = Salary.builder()
//                .departmentId(departmentId)
//                .employeeId(employeeId)
//                .month(String.valueOf(numberOfMonth))
//                .year(String.valueOf(numberOfYear))
//                .salary(salaryNumber)
//                .build();
//        SqlRowSet salaryDataRow = jdbcTemplate.queryForRowSet("SELECT * FROM salary " +
//                "WHERE employee_id=? AND month=? AND year=?", salary.getEmployeeId(), salary.getMonth(), salary.getYear());
//        if (salaryDataRow.next()) {
//            salary.setId(salaryDataRow.getLong("id"));
//        }
//
//        return salary;
//    }
//
//    // получить оклад
//    private Integer makeWage(ResultSet rs) throws SQLException {
//        return rs.getInt("wage");
//    }
//
//    // получить bonus
//    private Integer makeBonus(ResultSet rs) throws SQLException {
//        return rs.getInt("bonus");
//    }
//
//    // количество отработанных дней
//    private Integer makeCountDayAttendance(ResultSet rs) throws SQLException {
//        return rs.getInt("count");
//    }
//
//    // kpi
//    private Double makePersonalKpi(ResultSet rs) throws SQLException {
//        return rs.getDouble("personal_kpi");
//    }
//
//    // kpi
//    private Double makeTeamKpi(ResultSet rs) throws SQLException {
//        return rs.getDouble("team_kpi");
//    }
//
//    // kpi
//    private Double makeCommonKpi(ResultSet rs) throws SQLException {
//        return rs.getDouble("common_kpi");
//    }
//
//    // department id
//    private Integer makeDepartmentId(ResultSet rs) throws SQLException {
//        return rs.getInt("department_id");
//    }
//
//    // month
//    private Integer makeMonth(ResultSet rs) throws SQLException {
//        return rs.getInt("extract");
//    }
//
//    //year
//    private Integer makeYear(ResultSet rs) throws SQLException {
//        return rs.getInt("extract");
//    }
//
//    // first day
//    private String makeFirstDayDate(String end) {
//        String[] split = end.split("-");
//        String month = split[1];
//        String year = split[0];
//        return year + "-" + month + "-" + "01";
//    }
//}
