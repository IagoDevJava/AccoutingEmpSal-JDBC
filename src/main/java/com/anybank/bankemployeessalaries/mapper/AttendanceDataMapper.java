package com.anybank.bankemployeessalaries.mapper;

import com.anybank.bankemployeessalaries.dto.AttendanceDataDto;
import com.anybank.bankemployeessalaries.model.AttendanceData;

import java.util.ArrayList;
import java.util.List;

public class AttendanceDataMapper {
    //AttendanceData to AttendanceDataDto
    public static AttendanceDataDto toAttendanceDataDto(AttendanceData attendanceData) {
        return AttendanceDataDto.builder()
                .id(attendanceData.getId())
                .dateAtt(attendanceData.getDateAtt())
                .employeeId(attendanceData.getEmployeeId())
                .attendanceStatus(attendanceData.getStatus())
                .build();
    }

    //AttendanceDataList to AttendanceDataDtoList
    public static List<AttendanceDataDto> toAttendanceDataDtoList(List<AttendanceData> attendanceData) {
        List<AttendanceDataDto> result = new ArrayList<>();
        for (AttendanceData attendanceData1 : attendanceData) {
            result.add(toAttendanceDataDto(attendanceData1));
        }
        return result;
    }
}
