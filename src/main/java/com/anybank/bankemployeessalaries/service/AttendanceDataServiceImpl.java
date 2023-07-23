package com.anybank.bankemployeessalaries.service;

import com.anybank.bankemployeessalaries.dto.AttendanceDataDto;
import com.anybank.bankemployeessalaries.exception.AttendanceDataNotFoundException;
import com.anybank.bankemployeessalaries.mapper.AttendanceDataMapper;
import com.anybank.bankemployeessalaries.model.AttendanceData;
import com.anybank.bankemployeessalaries.repository.AttendanceDataRepository;
import com.anybank.bankemployeessalaries.repository.DepartmentRepository;
import com.anybank.bankemployeessalaries.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AttendanceDataServiceImpl implements AttendanceDataService {
    private final AttendanceDataRepository attendanceDataRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    /**
     * Ввести данные за день
     */
    @Override
    public AttendanceDataDto addAttendanceData(AttendanceData attendanceData) {
        return AttendanceDataMapper.toAttendanceDataDto(attendanceDataRepository.save(attendanceData));
    }

    /**
     * Изменить данные за день
     */
    @Override
    public AttendanceDataDto updateAttendanceData(AttendanceData attendanceData, Long id) {
        AttendanceData attendanceById = attendanceDataRepository.findById(id)
                .orElseThrow(() -> new AttendanceDataNotFoundException("Attendance not found"));

        attendanceById.setId(id);
        attendanceById.setDateAtt(attendanceData.getDateAtt());
        attendanceById.setEmployeeId(attendanceData.getEmployeeId());
        attendanceById.setStatus(attendanceData.getStatus());

        return AttendanceDataMapper.toAttendanceDataDto(attendanceDataRepository.save(attendanceById));
    }

    /**
     * Удалить данные за день
     */
    @Override
    public void deleteAttendanceDataByDay(String date) {
        attendanceDataRepository.findAttendanceDataByDateAtt(LocalDate.parse(date))
                .orElseThrow(() -> new AttendanceDataNotFoundException("Attendance with date not found"));
        attendanceDataRepository.deleteAttendanceDataByDateAtt(LocalDate.parse(date));
    }

    /**
     * Удалить все данные
     */
    @Override
    public void deleteAttendanceData() {
        attendanceDataRepository.deleteAll();
    }

    /**
     * Удалить данные по сотруднику
     */
    @Override
    public void deleteAttendanceDataByEmployee(Integer employeeId) {
        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new AttendanceDataNotFoundException("Employee not found"));
        attendanceDataRepository.deleteAttendanceDataByEmployeeId(employeeId);
    }

    /**
     * получить данные за период
     */
    @Override
    public List<AttendanceDataDto> getAttendanceDataByPeriod(String start, String end) {
        return AttendanceDataMapper.toAttendanceDataDtoList(
                attendanceDataRepository
                        .findAttendanceDataByDateAttBetween(LocalDate.parse(start), LocalDate.parse(end)));
    }

    /**
     * получить данные по сотруднику за период
     */
    @Override
    public List<AttendanceDataDto> getAttendanceDataByPeriodByEmployee(Integer employeeId, String start, String end) {
        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new AttendanceDataNotFoundException("Employee not found"));

        return AttendanceDataMapper.toAttendanceDataDtoList(
                attendanceDataRepository.findAttendanceDataByEmployeeIdAndDateAttBetween(
                        employeeId, LocalDate.parse(start), LocalDate.parse(end))
        );
    }

    /**
     * получить данные по отделу за период
     */
    @Override
    public List<AttendanceDataDto> getAttendanceDataByPeriodByDepartment(
            Integer departmentId, String start, String end) {
        departmentRepository.findById(departmentId)
                .orElseThrow(() -> new AttendanceDataNotFoundException("Department not found"));

        return AttendanceDataMapper.toAttendanceDataDtoList(
                attendanceDataRepository.findAttendanceDataByDepIdToPeriod(
                        departmentId, LocalDate.parse(start), LocalDate.parse(end))
        );
    }

    /**
     * получить данные по id
     */
    @Override
    public AttendanceDataDto getAttendanceDataById(Long id) {
        return AttendanceDataMapper.toAttendanceDataDto(attendanceDataRepository.findById(id)
                .orElseThrow(() -> new AttendanceDataNotFoundException("Attendance not found")));
    }
}
