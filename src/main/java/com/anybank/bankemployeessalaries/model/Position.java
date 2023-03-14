package com.anybank.bankemployeessalaries.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
public class Position {
    private int id;
    @NotBlank
    private String post;
    @NotBlank
    private Department department;
    @NotBlank
    private Grade grade;
}
