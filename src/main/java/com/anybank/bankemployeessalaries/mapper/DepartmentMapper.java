package com.anybank.bankemployeessalaries.mapper;

import com.anybank.bankemployeessalaries.dto.DepartmentDto;
import com.anybank.bankemployeessalaries.model.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentMapper {
    //Department to DepartmentDto
    public static DepartmentDto toDepartmentDto(Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .phone(department.getPhone())
                .email(department.getEmail())
                .address(department.getAddress())
                .headId(department.getHeadEmpId())
                .build();
    }

    //DepartmentList to DepartmentDtoList
    public static List<DepartmentDto> toDepartmentDtoList(List<Department> departments) {
        List<DepartmentDto> result = new ArrayList<>();
        for (Department department : departments) {
            result.add(toDepartmentDto(department));
        }
        return result;
    }
}
