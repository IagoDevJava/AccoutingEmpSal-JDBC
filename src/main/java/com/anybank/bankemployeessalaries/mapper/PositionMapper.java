package com.anybank.bankemployeessalaries.mapper;

import com.anybank.bankemployeessalaries.dto.GradeDto;
import com.anybank.bankemployeessalaries.dto.PositionDto;
import com.anybank.bankemployeessalaries.model.Grade;
import com.anybank.bankemployeessalaries.model.Position;

import java.util.ArrayList;
import java.util.List;

public class PositionMapper {
    //Position to PositionDto
    public static PositionDto toPositionDto(Position position) {
        return PositionDto.builder()
                .id(position.getId())
                .post(position.getPost())
                .departmentId(position.getDepartmentId())
                .gradeId(position.getGradeId())
                .build();
    }

    //PositionList to PositionDtoList
    public static List<PositionDto> toPositionDtoList(List<Position> positions) {
        List<PositionDto> result = new ArrayList<>();
        for (Position position : positions) {
            result.add(toPositionDto(position));
        }
        return result;
    }
}
