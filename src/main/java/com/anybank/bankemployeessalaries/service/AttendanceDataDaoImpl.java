//package com.anybank.bankemployeessalaries.dao.impl;
//
//import com.anybank.bankemployeessalaries.dao.AttendanceDataDao;
//import com.anybank.bankemployeessalaries.model.AttendanceData;
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
//public class AttendanceDataDaoImpl implements AttendanceDataDao {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public AttendanceDataDaoImpl(@Lazy JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    /**
//     * Ввести данные за день
//     */
//    @Override
//    public AttendanceData addAttendanceData(AttendanceData attendanceData) {
//        String sql = "INSERT INTO attendance_data(date_att, employee_id, status) " +
//                "VALUES (?, ?, ?)";
//        jdbcTemplate.update(
//                sql, attendanceData.getDateAtt(), attendanceData.getEmployeeId(), attendanceData.getJobStatus()
//        );
//        SqlRowSet attRows = jdbcTemplate.queryForRowSet(
//                "SELECT * FROM attendance_data WHERE date_att=? AND employee_id=?",
//                attendanceData.getDateAtt(), attendanceData.getEmployeeId()
//        );
//        if (attRows.next()) {
//            attendanceData.setId(attRows.getLong("id"));
//        }
//
//        log.info("Добавили данные о посещении № {} в БД", attendanceData.getId());
//
//        return attendanceData;
//    }
//
//    /**
//     * Изменить данные за день
//     */
//    @Override
//    public AttendanceData updateAttendanceData(AttendanceData attendanceData) {
//        String sql = "UPDATE attendance_data" +
//                " SET date_att=?," +
//                " employee_id=?," +
//                " status=? " +
//                " WHERE id = ?";
//
//        jdbcTemplate.update(
//                sql, attendanceData.getDateAtt(), attendanceData.getEmployeeId(), attendanceData.getJobStatus()
//        );
//        log.info("Данные о посещении обновлены {}", attendanceData.getId());
//        return attendanceData;
//    }
//
//    /**
//     * Удалить данные за день
//     */
//    @Override
//    public void deleteAttendanceDataByDay(String attendanceDate) {
//        String sql = "DELETE FROM attendance_data WHERE date_att=?";
//        jdbcTemplate.update(sql, LocalDate.parse(attendanceDate, DateTimeFormatter.BASIC_ISO_DATE));
//        log.info("Удалены данные о посещаемости из БД по дню {}", attendanceDate);
//    }
//
//    /**
//     * Удалить все данные
//     */
//    @Override
//    public void deleteAttendanceData() {
//        String sql = "DELETE FROM attendance_data";
//        jdbcTemplate.update(sql);
//        log.info("Удалены все данные о посещаемости из БД");
//    }
//
//    /**
//     * Удалить данные по сотруднику
//     */
//    @Override
//    public void deleteAttendanceDataByEmployee(Integer employeeId) {
//        String sql = "DELETE FROM attendance_data WHERE employee_id=?";
//        jdbcTemplate.update(sql, employeeId);
//        log.info("Удалены данные о посещаемости из БД по сотруднику {}", employeeId);
//    }
//
//    /**
//     * получить данные за период
//     */
//    @Override
//    public List<AttendanceData> getAttendanceDataByPeriod(String start, String end) {
//        String sql = "SELECT * FROM attendance_data WHERE date_att BETWEEN ? AND ?";
//        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.BASIC_ISO_DATE);
//        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.BASIC_ISO_DATE);
//        return jdbcTemplate.query(
//                sql, (rs, rowNum) -> makeAttendanceData(rs), startDate, endDate.minusDays(1)
//        );
//    }
//
//    /**
//     * получить данные по сотруднику за период
//     */
//    @Override
//    public List<AttendanceData> getAttendanceDataByPeriodByEmployee(Integer employeeId, String start, String end) {
//        String sql = "SELECT * FROM attendance_data WHERE employee_id=? AND date_att BETWEEN ? AND ?";
//        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.BASIC_ISO_DATE);
//        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.BASIC_ISO_DATE);
//        return jdbcTemplate.query(
//                sql, (rs, rowNum) -> makeAttendanceData(rs), employeeId, startDate, endDate.minusDays(1)
//        );
//    }
//
//    /**
//     * получить данные по отделу за период
//     */
//    @Override
//    public List<AttendanceData> getAttendanceDataByPeriodByDepartment(Integer departmentId, String start, String end) {
//        String sql = "SELECT * FROM attendance_data at " +
//                "LEFT JOIN employee e on e.id = at.employee_id" +
//                " WHERE e.department_id=? AND at.date_att BETWEEN ? AND ?";
//        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.BASIC_ISO_DATE);
//        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.BASIC_ISO_DATE);
//        return jdbcTemplate.query(
//                sql, (rs, rowNum) -> makeAttendanceData(rs), departmentId, startDate, endDate.minusDays(1)
//        );
//    }
//
//    /**
//     * получить данные
//     */
//    @Override
//    public List<AttendanceData> getAttendanceData() {
//        String sql = "SELECT * FROM attendance_data";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> makeAttendanceData(rs));
//    }
//
//    /**
//     * получить данные по сотруднику
//     */
//    @Override
//    public List<AttendanceData> getAttendanceDataByEmployee(Integer employeeId) {
//        String sql = "SELECT * FROM attendance_data WHERE employee_id=?";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> makeAttendanceData(rs), employeeId);
//    }
//
//    /**
//     * получить данные по отделу
//     */
//    @Override
//    public List<AttendanceData> getAttendanceDataByDepartment(Integer departmentId) {
//        String sql = "SELECT * FROM attendance_data at " +
//                "LEFT JOIN employee e on e.id = at.employee_id" +
//                " WHERE e.department_id=?";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> makeAttendanceData(rs), departmentId);
//    }
//
//    private AttendanceData makeAttendanceData(ResultSet rs) throws SQLException {
//        AttendanceData attendanceData = AttendanceData.builder()
//                .id(rs.getInt("id"))
//                .dateAtt(rs.getDate("date_att").toLocalDate())
//                .employeeId(rs.getInt("employee_id"))
//                .jobStatus(JobStatus.valueOf(rs.getString("status").toUpperCase()))
//                .build();
//        if (attendanceData.getDateAtt() == null) {
//            attendanceData = null;
//        }
//        return attendanceData;
//    }
//}
