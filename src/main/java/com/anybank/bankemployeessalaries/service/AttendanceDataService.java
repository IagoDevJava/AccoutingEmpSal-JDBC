package com.anybank.bankemployeessalaries.service;

import com.anybank.bankemployeessalaries.dao.AttendanceDataDao;
import com.anybank.bankemployeessalaries.model.AttendanceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceDataService {
    private final AttendanceDataDao attendanceDataDao;

    @Autowired
    public AttendanceDataService(AttendanceDataDao attendanceDataDao) {
        this.attendanceDataDao = attendanceDataDao;
    }

    /**
     * Ввести данные за день
     */
    public AttendanceData addAttendanceData(AttendanceData attendanceData) {
        return attendanceDataDao.addAttendanceData(attendanceData);
    }

    /**
     * Изменить данные за день
     */
    public AttendanceData updateAttendanceData(AttendanceData attendanceData) {
        return attendanceDataDao.updateAttendanceData(attendanceData);
    }

    /**
     * Удалить данные за день
     */
    public void deleteAttendanceDataByDay(String date) {
        attendanceDataDao.deleteAttendanceDataByDay(date);
    }

    /**
     * Удалить все данные
     */
    public void deleteAttendanceData() {
        attendanceDataDao.deleteAttendanceData();
    }

    /**
     * Удалить данные по сотруднику
     */
    public void deleteAttendanceDataByEmployee(Integer employeeId) {
        attendanceDataDao.deleteAttendanceDataByEmployee(employeeId);
    }

    /**
     * получить данные за период
     */
    public List<AttendanceData> getAttendanceDataByPeriod(String start, String end) {
        return attendanceDataDao.getAttendanceDataByPeriod(start, end);
    }

    /**
     * получить данные по сотруднику за период
     */
    public List<AttendanceData> getAttendanceDataByPeriodByEmployee(Integer employeeId, String start, String end) {
        return attendanceDataDao.getAttendanceDataByPeriodByEmployee(employeeId, start, end);
    }

    /**
     * получить данные по отделу за период
     */
    public List<AttendanceData> getAttendanceDataByPeriodByDepartment(Integer departmentId, String start, String end) {
        return attendanceDataDao.getAttendanceDataByPeriodByDepartment(departmentId, start, end);
    }

    /**
     * получить данные
     */
    public List<AttendanceData> getAttendanceData() {
        return attendanceDataDao.getAttendanceData();
    }

    /**
     * получить данные по сотруднику
     */
    public List<AttendanceData> getAttendanceDataByEmployee(Integer employeeId) {
        return attendanceDataDao.getAttendanceDataByEmployee(employeeId);
    }

    /**
     * получить данные по отделу
     */
    public List<AttendanceData> getAttendanceDataByDepartment(Integer departmentId) {
        return attendanceDataDao.getAttendanceDataByDepartment(departmentId);
    }
}
