package com.anybank.bankemployeessalaries.controller;

import com.anybank.bankemployeessalaries.model.AttendanceData;
import com.anybank.bankemployeessalaries.service.AttendanceDataService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@Validated
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
    public ResponseEntity<AttendanceData> addAttendanceData(
            @Valid @RequestBody AttendanceData attendanceData
    ) {
        return ResponseEntity.ok(attendanceDataService.addAttendanceData(attendanceData));
    }

    /**
     * Изменить данные за день
     */
    @PatchMapping()
    public ResponseEntity<AttendanceData> updateAttendanceData(
            @RequestBody AttendanceData attendanceData
    ) {
        return ResponseEntity.ok(attendanceDataService.updateAttendanceData(attendanceData));
    }

    /**
     * Удалить данные за день
     */
    @DeleteMapping("/{date}")
    public ResponseEntity<Void> deleteAttendanceDataByDay(
            @DateTimeFormat @PathVariable String date
    ) {
        attendanceDataService.deleteAttendanceDataByDay(date);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удалить все данные
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAttendanceData() {
        attendanceDataService.deleteAttendanceData();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удалить данные по сотруднику
     */
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteAttendanceDataByEmployee(
            @PositiveOrZero @PathVariable Integer employeeId
    ) {
        attendanceDataService.deleteAttendanceDataByEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * получить данные за период
     */
    @GetMapping
    public ResponseEntity<List<AttendanceData>> getAttendanceDataByPeriod(
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end
    ) {
        return ResponseEntity.ok(attendanceDataService.getAttendanceDataByPeriod(
                Objects.requireNonNullElse(start, String.valueOf(LocalDate.now().minusYears(1))),
                Objects.requireNonNullElse(end, String.valueOf(LocalDate.now())))
        );
    }

    /**
     * получить данные по сотруднику за период
     */
    @GetMapping("/{employeeId}")
    public ResponseEntity<List<AttendanceData>> getAttendanceDataByPeriodByEmployee(
            @PositiveOrZero @PathVariable Integer employeeId,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end
    ) {
        return ResponseEntity.ok(attendanceDataService.getAttendanceDataByPeriodByEmployee(
                employeeId,
                Objects.requireNonNullElse(start, String.valueOf(LocalDate.now().minusYears(1))),
                Objects.requireNonNullElse(end, String.valueOf(LocalDate.now())))
        );
    }

    /**
     * получить данные по отделу за период
     */
    @GetMapping("/{departmentId}")
    public ResponseEntity<List<AttendanceData>> getAttendanceDataByPeriodByDepartment(
            @PositiveOrZero @PathVariable Integer departmentId,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end
    ) {
        return ResponseEntity.ok(attendanceDataService.getAttendanceDataByPeriodByDepartment(
                departmentId,
                Objects.requireNonNullElse(start, String.valueOf(LocalDate.now().minusYears(1))),
                Objects.requireNonNullElse(end, String.valueOf(LocalDate.now())))
        );
    }
}
