package com.anybank.bankemployeessalaries.mapper;

import com.anybank.bankemployeessalaries.dto.EmployeeDto;
import com.anybank.bankemployeessalaries.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {
    //Employee to EmployeeDto
    public static EmployeeDto toEmployeeDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .surname(employee.getSurname())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .positionId(employee.getPositionId())
                .gender(employee.getGender())
                .departmentId(employee.getDepartmentId())
                .phone(employee.getPhone())
                .email(employee.getEmail())
                .workScheduleId(employee.getWorkScheduleId())
                .dateOfAdmission(employee.getDateOfAdmission())
                .jobStatus(employee.getJobStatus())
                .dateOfDismissal(employee.getDateOfDismissal())
                .build();
    }

    //EmployeeList to EmployeeDtoList
    public static List<EmployeeDto> toEmployeeDtoList(List<Employee> employees) {
        List<EmployeeDto> result = new ArrayList<>();
        for (Employee employee : employees) {
            result.add(toEmployeeDto(employee));
        }
        return result;
    }
}
