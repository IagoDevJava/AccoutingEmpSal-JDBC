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
    @Column
    Double wage;
    @NotNull
    @Column
    Double bonus;
    @NotNull
    @Column(name = "position_id")
    Integer positionId;

    public void setId(Integer id) {
        if (id != null) this.id = id;
    }

    public void setWage(Double wage) {
        if (wage != null) this.wage = wage;
    }

    public void setBonus(Double bonus) {
        if (bonus != null) this.bonus = bonus;
    }

    public void setPositionId(Integer positionId) {
        if (positionId != null) this.positionId = positionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalariesData that = (SalariesData) o;
        return Objects.equals(id, that.id)
                && Objects.equals(wage, that.wage)
                && Objects.equals(bonus, that.bonus)
                && Objects.equals(positionId, that.positionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wage, bonus, positionId);
    }
}