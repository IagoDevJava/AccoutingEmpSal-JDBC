package com.anybank.bankemployeessalaries.model;

import com.anybank.bankemployeessalaries.enum_model.JobStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false, length = 30)
    String surname;
    @Column(nullable = false, length = 30)
    String firstname;
    @Column(length = 30)
    String lastname;
    @Column(nullable = false, length = 30)
    String gender;
    @Column(name = "department_id")
    Integer departmentId;
    @Column
    @Pattern(regexp = "^[1-9]-\\d\\d\\d$")
    String phone;
    @Column(nullable = false)
    @Email
    String email;
    @Column(name = "position_id")
    Integer positionId;
    @Column(name = "work_schedule_id")
    Integer workScheduleId;
    @Column(nullable = false, name = "date_of_admission")
    @DateTimeFormat
    @FutureOrPresent
    LocalDateTime dateOfAdmission;
    @Column(name = "date_of_dismissal")
    @DateTimeFormat
    @PastOrPresent
    LocalDateTime dateOfDismissal;
    @Column(name = "job_status")
    @Enumerated(EnumType.STRING)
    JobStatus jobStatus;

    public void setId(Integer id) {
        if (id != null) this.id = id;
    }

    public void setSurname(String surname) {
        if (surname != null) this.surname = surname;
    }

    public void setFirstname(String firstname) {
        if (firstname != null) this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        if (lastname != null) this.lastname = lastname;
    }

    public void setGender(String gender) {
        if (gender != null) this.gender = gender;
    }

    public void setDepartmentId(Integer departmentId) {
        if (departmentId != null) this.departmentId = departmentId;
    }

    public void setPhone(String phone) {
        if (phone != null) this.phone = phone;
    }

    public void setEmail(String email) {
        if (email != null) this.email = email;
    }

    public void setPositionId(Integer positionId) {
        if (positionId != null) this.positionId = positionId;
    }

    public void setWorkScheduleId(Integer workScheduleId) {
        if (workScheduleId != null) this.workScheduleId = workScheduleId;
    }

    public void setDateOfAdmission(LocalDateTime dateOfAdmission) {
        if (dateOfAdmission != null) this.dateOfAdmission = dateOfAdmission;
    }

    public void setDateOfDismissal(LocalDateTime dateOfDismissal) {
        if (dateOfDismissal != null) this.dateOfDismissal = dateOfDismissal;
    }

    public void setJobStatus(JobStatus jobStatus) {
        if (jobStatus != null) this.jobStatus = jobStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id)
                && Objects.equals(surname, employee.surname)
                && Objects.equals(firstname, employee.firstname)
                && Objects.equals(lastname, employee.lastname)
                && Objects.equals(gender, employee.gender)
                && Objects.equals(departmentId, employee.departmentId)
                && Objects.equals(phone, employee.phone)
                && Objects.equals(email, employee.email)
                && Objects.equals(positionId, employee.positionId)
                && Objects.equals(workScheduleId, employee.workScheduleId)
                && Objects.equals(dateOfAdmission, employee.dateOfAdmission)
                && Objects.equals(dateOfDismissal, employee.dateOfDismissal)
                && jobStatus == employee.jobStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, firstname, lastname, gender, departmentId, phone,
                email, positionId, workScheduleId, dateOfAdmission, dateOfDismissal, jobStatus);
    }
}
