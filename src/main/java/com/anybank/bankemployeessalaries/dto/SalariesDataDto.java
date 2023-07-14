package com.anybank.bankemployeessalaries.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalariesDataDto {
    Integer id;
    Integer gradeId;
    Integer wage;
    Integer bonus;
    Double personalKpi;
    Double teamKpi;
    Double commonKpi;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalariesDataDto that = (SalariesDataDto) o;
        return Objects.equals(id, that.id) && Objects.equals(gradeId, that.gradeId) && Objects.equals(wage, that.wage) && Objects.equals(bonus, that.bonus) && Objects.equals(personalKpi, that.personalKpi) && Objects.equals(teamKpi, that.teamKpi) && Objects.equals(commonKpi, that.commonKpi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gradeId, wage, bonus, personalKpi, teamKpi, commonKpi);
    }
}