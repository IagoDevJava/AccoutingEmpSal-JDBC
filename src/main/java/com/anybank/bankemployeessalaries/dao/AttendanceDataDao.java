package com.anybank.bankemployeessalaries.dao;

import com.anybank.bankemployeessalaries.model.AttendanceData;

import java.util.List;

public interface AttendanceDataDao {

    /**
     * Ввести данные за день
     */
    AttendanceData addAttendanceData(AttendanceData attendanceData);

    /**
     * Изменить данные за день
     */
    AttendanceData updateAttendanceData(AttendanceData attendanceData);

    /**
     * Удалить данные за день
     */
    void deleteAttendanceDataByDay(String attendanceDate);

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
    List<AttendanceData> getAttendanceDataByPeriod(String start, String end);

    /**
     * получить данные по сотруднику за период
     */
    List<AttendanceData> getAttendanceDataByPeriodByEmployee(Integer employeeId, String start, String end);

    /**
     * получить данные по отделу за период
     */
    List<AttendanceData> getAttendanceDataByPeriodByDepartment(Integer departmentId, String start, String end);

    /**
     * получить данные
     */
    List<AttendanceData> getAttendanceData();

    /**
     * получить данные по сотруднику
     */
    List<AttendanceData> getAttendanceDataByEmployee(Integer employeeId);

    /**
     * получить данные по отделу
     */
    List<AttendanceData> getAttendanceDataByDepartment(Integer departmentId);
}
