package com.anybank.bankemployeessalaries.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Slf4j
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Employee {
    int id;
    @NotBlank
    String surname;
    @NotBlank
    String firstname;
    @NotBlank
    String lastname;
    @NotBlank
    String gender;
    Integer departmentId;
    @NotBlank
    @Pattern(regexp = "^[1-9]-\\d\\d\\d$")
    String phone;
    @NotBlank
    @Email
    String email;
    Integer positionId;
    Integer workScheduleId;
    @NotNull
    @DateTimeFormat
    LocalDateTime dateOfAdmission;
    @DateTimeFormat
    LocalDateTime dateOfDismissal;
    JobStatus jobStatus;
}
