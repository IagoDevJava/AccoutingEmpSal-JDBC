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
@Table(name = "work_schedules")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotNull
    @Column(name = "work_days")
    Integer workDay;
    @NotNull
    @Column(name = "Week_days")
    Integer weekDay;
    @NotNull
    @Column(name = "hours")
    Integer workHour;

    public void setId(Integer id) {
        if (id != null) this.id = id;
    }

    public void setWorkDay(Integer workDay) {
        if (workDay != null) this.workDay = workDay;
    }

    public void setWeekDay(Integer weekDay) {
        if (weekDay != null) this.weekDay = weekDay;
    }

    public void setWorkHour(Integer workHour) {
        if (workHour != null) this.workHour = workHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkSchedule that = (WorkSchedule) o;
        return Objects.equals(id, that.id)
                && Objects.equals(workDay, that.workDay)
                && Objects.equals(weekDay, that.weekDay)
                && Objects.equals(workHour, that.workHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workDay, weekDay, workHour);
    }
}
