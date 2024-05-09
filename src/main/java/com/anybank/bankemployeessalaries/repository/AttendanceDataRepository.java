package com.anybank.bankemployeessalaries.repository;

import com.anybank.bankemployeessalaries.model.AttendanceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceDataRepository extends JpaRepository<AttendanceData, Long> {
    void deleteAttendanceDataByDateAtt(LocalDate dateAtt);

    void deleteAttendanceDataByEmployee(Integer empId);

    List<AttendanceData> findAttendanceDataByDateAttBetween(LocalDate start, LocalDate end);

    List<AttendanceData> findAttendanceDataByEmployeeAndDateAttBetween(Integer empId, LocalDate start, LocalDate end);

    @Query(value = """
            SELECT at.id, at.date_att, at.employee_id, at.status
            FROM attendance_data AS at
                     LEFT JOIN employees e on e.id = at.employee_id
            WHERE e.department_id = ?
              AND (date_att between ? AND ?)""", nativeQuery = true)
    List<AttendanceData> findAttendanceDataByDepIdToPeriod(Integer depId, LocalDate start, LocalDate end);

    Optional<AttendanceData> findAttendanceDataByDateAtt(LocalDate date);
//    List<AttendanceData> findAttendanceDataByEmployeeId(Integer id);
}