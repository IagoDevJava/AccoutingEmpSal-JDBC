package com.anybank.bankemployeessalaries.service;

import com.anybank.bankemployeessalaries.dto.AttendanceDataDto;
import com.anybank.bankemployeessalaries.exception.AttendanceDataNotFoundException;
import com.anybank.bankemployeessalaries.mapper.AttendanceDataMapper;
import com.anybank.bankemployeessalaries.model.AttendanceData;
import com.anybank.bankemployeessalaries.repository.AttendanceDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AttendanceDataServiceImpl implements AttendanceDataService {
    private final AttendanceDataRepository attendanceDataRepository;

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
        attendanceById.setJobStatus(attendanceData.getJobStatus());

        return AttendanceDataMapper.toAttendanceDataDto(attendanceDataRepository.save(attendanceById));
    }

    /**
     * Удалить данные за день
     */
    @Override
    public void deleteAttendanceDataByDay(String date) {
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
