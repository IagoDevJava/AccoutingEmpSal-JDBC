package com.anybank.bankemployeessalaries.mapper;

import com.anybank.bankemployeessalaries.dto.KpiDto;
import com.anybank.bankemployeessalaries.model.Kpi;

import java.util.ArrayList;
import java.util.List;

public class KpiMapper {
    //Kpi to KpiDto
    public static KpiDto toKpiDto(Kpi kpi) {
        return KpiDto.builder()
                .id(kpi.getId())
                .salaryId(kpi.getSalaryId())
                .personalKpi(kpi.getPersonalKpi())
                .teamKpi(kpi.getTeamKpi())
                .commonKpi(kpi.getCommonKpi())
                .build();
    }

    //KpiList to KpiDtoList
    public static List<KpiDto> toKpiDtoList(List<Kpi> kpis) {
        List<KpiDto> result = new ArrayList<>();
        for (Kpi kpi : kpis) {
            result.add(toKpiDto(kpi));
        }
        return result;
    }
}
