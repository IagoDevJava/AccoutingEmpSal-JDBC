package com.anybank.bankemployeessalaries.mapper;

import com.anybank.bankemployeessalaries.dto.WorkScheduleDto;
import com.anybank.bankemployeessalaries.model.WorkSchedule;

import java.util.ArrayList;
import java.util.List;

public class WorkScheduleMapper {
    //WorkSchedule to WorkScheduleDto
    public static WorkScheduleDto toWorkScheduleDto(WorkSchedule workSchedule) {
        return WorkScheduleDto.builder()
                .id(workSchedule.getId())
                .workDay(workSchedule.getWorkDay())
                .weekDay(workSchedule.getWeekDay())
                .workHour(workSchedule.getWorkHour())
                .build();
    }

    //EmployeeList to EmployeeDtoList
    public static List<WorkScheduleDto> toWorkScheduleDtoList(List<WorkSchedule> workSchedules) {
        List<WorkScheduleDto> result = new ArrayList<>();
        for (WorkSchedule workSchedule : workSchedules) {
            result.add(toWorkScheduleDto(workSchedule));
        }
        return result;
    }
}
