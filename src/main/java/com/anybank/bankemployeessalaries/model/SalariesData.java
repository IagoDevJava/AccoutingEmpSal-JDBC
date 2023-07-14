package com.anybank.bankemployeessalaries.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "salaries_data")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalariesData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotNull
    @Column(name = "grade_id")
    Integer gradeId;
    @NotNull
    @Column
    Integer wage;
    @NotNull
    @Column
    Integer bonus;
    @Column(name = "personal_kpi")
    Double personalKpi;
    @Column(name = "teaml_kpi")
    Double teamKpi;
    @Column(name = "common_kpi")
    Double commonKpi;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public void setWage(Integer wage) {
        this.wage = wage;
    }

    public void setPersonalKpi(Double personalKpi) {
        this.personalKpi = personalKpi;
    }

    public void setTeamKpi(Double teamKpi) {
        this.teamKpi = teamKpi;
    }

    public void setCommonKpi(Double commonKpi) {
        this.commonKpi = commonKpi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalariesData that = (SalariesData) o;
        return Objects.equals(id, that.id)
                && Objects.equals(gradeId, that.gradeId)
                && Objects.equals(wage, that.wage)
                && Objects.equals(personalKpi, that.personalKpi)
                && Objects.equals(teamKpi, that.teamKpi)
                && Objects.equals(commonKpi, that.commonKpi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gradeId, wage, personalKpi, teamKpi, commonKpi);
    }
}