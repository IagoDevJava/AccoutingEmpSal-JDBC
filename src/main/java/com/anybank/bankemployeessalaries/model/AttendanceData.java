package com.anybank.bankemployeessalaries.model;

import com.anybank.bankemployeessalaries.enum_model.JobStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attendance_dataies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttendanceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotBlank
    @Column(name = "date_att")
    LocalDate dateAtt;
    @NotBlank
    @PositiveOrZero
    @Column(name = "employees_id")
    int employeeId;
    @NotBlank
    @Enumerated(EnumType.STRING)
    JobStatus jobStatus;

    public void setId(long id) {
        this.id = id;
    }

    public void setDateAtt(LocalDate dateAtt) {
        this.dateAtt = dateAtt;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceData that = (AttendanceData) o;
        return id == that.id && employeeId == that.employeeId && Objects.equals(dateAtt, that.dateAtt) && jobStatus == that.jobStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateAtt, employeeId, jobStatus);
    }
}
