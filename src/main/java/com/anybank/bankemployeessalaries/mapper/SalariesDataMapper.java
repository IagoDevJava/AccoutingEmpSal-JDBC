package com.anybank.bankemployeessalaries.mapper;

import com.anybank.bankemployeessalaries.dto.SalariesDataDto;
import com.anybank.bankemployeessalaries.model.SalariesData;

import java.util.ArrayList;
import java.util.List;

public class SalariesDataMapper {
    //SalariesData to SalariesDataDto
    public static SalariesDataDto toSalariesDataDto(SalariesData salariesData) {
        return SalariesDataDto.builder()
                .id(salariesData.getId())
                .wage(salariesData.getWage())
                .bonus(salariesData.getBonus())
                .positionId(salariesData.getPosition().getId())
                .build();
    }

    //SalariesDataList to SalariesDataDtoList
    public static List<SalariesDataDto> toGSalariesDataDtoList(List<SalariesData> salariesDataList) {
        List<SalariesDataDto> result = new ArrayList<>();
        for (SalariesData salariesData : salariesDataList) {
            result.add(toSalariesDataDto(salariesData));
        }
        return result;

    }
}
