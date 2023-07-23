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
@Table(name = "kpis")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Kpi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    @Column(name = "salary_id")
    Integer salaryId;
    @NotNull
    @Column(name = "personal_kpi")
    Double personalKpi;
    @NotNull
    @Column(name = "team_kpi")
    Double teamKpi;
    @NotNull
    @Column(name = "common_kpi")
    Double commonKpi;

    public void setId(Long id) {
        if (id != null) this.id = id;
    }

    public void setSalaryId(Integer salaryId) {
        if (salaryId != null) this.salaryId = salaryId;
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
        Kpi kpi = (Kpi) o;
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
