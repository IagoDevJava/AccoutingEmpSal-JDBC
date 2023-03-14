package com.anybank.bankemployeessalaries.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
public class Department {
    private int id;
    @NotBlank
    private String name;
    private String address;
    @NotBlank
    @Pattern(regexp = "\\d(1)-\\d(1,3)")
    private String phone;
    @Email
    @NotBlank
    private String email;
    private Employee head;
}
