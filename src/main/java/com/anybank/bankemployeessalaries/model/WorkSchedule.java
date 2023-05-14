package com.anybank.bankemployeessalaries.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

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
    @Column(nullable = false, name = "work_days")
    Integer workDay;
    @Column(nullable = false, name = "Week_days")
    Integer weekDay;
    @Column(nullable = false, name = "hours")
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
}
