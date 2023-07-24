package com.anybank.bankemployeessalaries.mapper;

import com.anybank.bankemployeessalaries.dto.SalaryDto;
import com.anybank.bankemployeessalaries.model.Salary;

import java.util.ArrayList;
import java.util.List;

public class SalaryMapper {
    //Salary to SalaryDto
    public static SalaryDto toSalaryDto(Salary salary) {
        return SalaryDto.builder()
                .id(salary.getId())
                .employeeId(salary.getEmployeeId())
                .departmentId(salary.getDepartmentId())
                .month(salary.getMonth())
                .year(salary.getYear())
                .payment(salary.getPayment())
                .build();
    }

    //SalaryList to SalaryDtoList
    public static List<SalaryDto> toSalaryDtoList(List<Salary> salaries) {
        List<SalaryDto> result = new ArrayList<>();
        for (Salary salary : salaries) {
            result.add(toSalaryDto(salary));
        }
        return result;
    }
}
