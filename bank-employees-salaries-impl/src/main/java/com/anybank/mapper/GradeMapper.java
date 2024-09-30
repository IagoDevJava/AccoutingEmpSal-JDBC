package com.anybank.mapper;

import com.anybank.dto.GradeDto;
import com.anybank.model.Grade;

import java.util.ArrayList;
import java.util.List;

public class GradeMapper {
    //Grade to GradeDto
    public static GradeDto toGradeDto(Grade grade) {
        return GradeDto.builder()
                .id(grade.getId())
                .name(grade.getName())
                .build();
    }

    //GradeList to GradeDtoList
    public static List<GradeDto> toGradeDtoList(List<Grade> grades) {
        List<GradeDto> result = new ArrayList<>();
        for (Grade grade : grades) {
            result.add(toGradeDto(grade));
        }
        return result;
    }
}
