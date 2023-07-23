package com.anybank.bankemployeessalaries.dto;

import lombok.*;

import java.util.Objects;

@Data
@Builder
public class KpiDto {
    Long id;
    Integer salaryId;
    Double personalKpi;
    Double teamKpi;
    Double commonKpi;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KpiDto kpi = (KpiDto) o;
        return Objects.equals(id, kpi.id)
                && Objects.equals(salaryId, kpi.salaryId)
                && Objects.equals(personalKpi, kpi.personalKpi)
                && Objects.equals(teamKpi, kpi.teamKpi)
                && Objects.equals(commonKpi, kpi.commonKpi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salaryId, personalKpi, teamKpi, commonKpi);
    }
}
