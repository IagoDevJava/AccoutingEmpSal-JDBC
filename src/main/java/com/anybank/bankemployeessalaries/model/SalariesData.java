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
        if (id != null) this.id = id;
    }

    public void setGradeId(Integer gradeId) {
        if (gradeId != null) this.gradeId = gradeId;
    }

    public void setWage(Integer wage) {
        if (wage != null) this.wage = wage;
    }

    public void setBonus(Integer bonus) {
        if (bonus != null) this.bonus = bonus;
    }

    public void setPersonalKpi(Double personalKpi) {
        if (personalKpi != null) this.personalKpi = personalKpi;
    }

    public void setTeamKpi(Double teamKpi) {
        if (teamKpi != null) this.teamKpi = teamKpi;
    }

    public void setCommonKpi(Double commonKpi) {
        if (commonKpi != null) this.commonKpi = commonKpi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalariesData that = (SalariesData) o;
        return Objects.equals(id, that.id) && Objects.equals(gradeId, that.gradeId) && Objects.equals(wage, that.wage) && Objects.equals(bonus, that.bonus) && Objects.equals(personalKpi, that.personalKpi) && Objects.equals(teamKpi, that.teamKpi) && Objects.equals(commonKpi, that.commonKpi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gradeId, wage, bonus, personalKpi, teamKpi, commonKpi);
    }
}