package com.anybank.bankemployeessalaries.repository;

import com.anybank.bankemployeessalaries.model.AttendanceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceDataRepository extends JpaRepository<AttendanceData, Long> {
    void deleteAttendanceDataByDateAtt(LocalDate dateAtt);
    void deleteAttendanceDataByEmployeeId(Integer empId);
    List<AttendanceData> findAttendanceDataByDateAttBetween(LocalDate start, LocalDate end);
    List<AttendanceData> findAttendanceDataByEmployeeIdAndDateAttBetween(Integer empId, LocalDate start, LocalDate end);
    @Query(value = "SELECT at.id, at.date_att, at.employees_id, at.status\n" +
            "FROM attendance_data AS at\n" +
            "         LEFT JOIN employees e on e.id = at.employees_id\n" +
            "WHERE e.department_id = ?\n" +
            "  AND (date_att between ? AND ?)", nativeQuery = true)
    List<AttendanceData> findAttendanceDataByDepIdToPeriod(Integer depId, LocalDate start, LocalDate end);
}