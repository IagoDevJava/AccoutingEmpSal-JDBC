package com.anybank.bankemployeessalaries.controller;

import com.anybank.bankemployeessalaries.model.AttendanceData;
import com.anybank.bankemployeessalaries.service.AttendanceDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/attendance-data")
public class AttendanceDataController {
    private final AttendanceDataService attendanceDataService;

    @Autowired
    public AttendanceDataController(AttendanceDataService attendanceDataService) {
        this.attendanceDataService = attendanceDataService;
    }

    /**
     * Ввести данные за день
     */
    @PostMapping()
    public AttendanceData addAttendanceData(@RequestBody AttendanceData attendanceData) {
        return attendanceDataService.addAttendanceData(attendanceData);
    }

    /**
     * Изменить данные за день
     */
    @PatchMapping()
    public AttendanceData updateAttendanceData(@RequestBody AttendanceData attendanceData) {
        return attendanceDataService.updateAttendanceData(attendanceData);
    }

    /**
     * Удалить данные за день
     */
    @DeleteMapping("/{date}")
    public void deleteAttendanceDataByDay(@PathVariable String date) {
        attendanceDataService.deleteAttendanceDataByDay(date);
    }

    /**
     * Удалить все данные
     */
    @DeleteMapping
    public void deleteAttendanceData() {
        attendanceDataService.deleteAttendanceData();
    }

    /**
     * Удалить данные по сотруднику
     */
    @DeleteMapping("/{employeeId}")
    public void deleteAttendanceDataByEmployee(@PathVariable Integer employeeId) {
        attendanceDataService.deleteAttendanceDataByEmployee(employeeId);
    }

    /**
     * получить данные за период
     */
    @GetMapping
    public List<AttendanceData> getAttendanceDataByPeriod(@RequestParam String start,
                                                          @RequestParam String end) {
        return attendanceDataService.getAttendanceDataByPeriod(start, end);
    }

    /**
     * получить данные по сотруднику за период
     */
    @GetMapping("/{employeeId}")
    public List<AttendanceData> getAttendanceDataByPeriodByEmployee(@PathVariable Integer employeeId,
                                                                    @RequestParam String start,
                                                                    @RequestParam String end) {
        return attendanceDataService.getAttendanceDataByPeriodByEmployee(employeeId, start, end);
    }

    /**
     * получить данные по отделу за период
     */
    @GetMapping("/{departmentId}")
    public List<AttendanceData> getAttendanceDataByPeriodByDepartment(@PathVariable Integer departmentId,
                                                                      @RequestParam String start,
                                                                      @RequestParam String end) {
        return attendanceDataService.getAttendanceDataByPeriodByDepartment(departmentId, start, end);
    }

    /**
     * получить данные
     */
    @GetMapping
    public List<AttendanceData> getAttendanceData() {
        return attendanceDataService.getAttendanceData();
    }

    /**
     * получить данные по сотруднику
     */
    @GetMapping("/{employeeId}")
    public List<AttendanceData> getAttendanceDataByEmployee(@PathVariable Integer employeeId) {
        return attendanceDataService.getAttendanceDataByEmployee(employeeId);
    }

    /**
     * получить данные по отделу
     */
    @GetMapping("/{departmentId}")
    public List<AttendanceData> getAttendanceDataByDepartment(@PathVariable Integer departmentId) {
        return attendanceDataService.getAttendanceDataByDepartment(departmentId);
    }
}
