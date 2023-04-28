package com.anybank.bankemployeessalaries.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

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
