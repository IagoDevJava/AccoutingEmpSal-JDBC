package com.anybank.bankemployeessalaries.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @Column(name = "personal_kpi")
    Double personalKpi;
    @NotNull
    @Column(name = "team_kpi")
    Double teamKpi;
    @NotNull
    @Column(name = "common_kpi")
    Double commonKpi;
    @NotNull
    @Column(name = "employee_id")
    Integer employeeId;
    @NotBlank
    @Column
    String month;
    @NotBlank
    @Column
    String year;

    public void setId(Long id) {
        if (id != null) this.id = id;
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

    public void setEmployeeId(Integer employeeId) {
        if (employeeId != null) this.employeeId = employeeId;
    }

    public void setMonth(String month) {
        if (month != null) this.month = month;
    }

    public void setYear(String year) {
        if (year != null) this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kpi kpi = (Kpi) o;
        return Objects.equals(id, kpi.id) && Objects.equals(personalKpi, kpi.personalKpi) && Objects.equals(teamKpi, kpi.teamKpi) && Objects.equals(commonKpi, kpi.commonKpi) && Objects.equals(employeeId, kpi.employeeId) && Objects.equals(month, kpi.month) && Objects.equals(year, kpi.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personalKpi, teamKpi, commonKpi, employeeId, month, year);
    }
}
