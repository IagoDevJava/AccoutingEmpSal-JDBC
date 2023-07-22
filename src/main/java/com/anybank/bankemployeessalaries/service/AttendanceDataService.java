package com.anybank.bankemployeessalaries.service;

import com.anybank.bankemployeessalaries.dto.AttendanceDataDto;
import com.anybank.bankemployeessalaries.model.AttendanceData;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface AttendanceDataService {
    /**
     * Ввести данные за день
     */
    AttendanceDataDto addAttendanceData(AttendanceData attendanceData);

    /**
     * Изменить данные за день
     */
    AttendanceDataDto updateAttendanceData(AttendanceData attendanceData, Long id);

    /**
     * Удалить данные за день
     */
    void deleteAttendanceDataByDay(String date);

    /**
     * Удалить все данные
     */
    void deleteAttendanceData();

    /**
     * Удалить данные по сотруднику
     */
    void deleteAttendanceDataByEmployee(Integer employeeId);

    /**
     * получить данные за период
     */
    List<AttendanceDataDto> getAttendanceDataByPeriod(String start, String end);

    /**
     * получить данные по сотруднику за период
     */
    List<AttendanceDataDto> getAttendanceDataByPeriodByEmployee(Integer employeeId, String start, String end);

    /**
     * получить данные по отделу за период
     */
    List<AttendanceDataDto> getAttendanceDataByPeriodByDepartment(Integer departmentId, String start, String end);

    /**
     * получить данные по id
     */
    AttendanceDataDto getAttendanceDataById(Long id);
}
