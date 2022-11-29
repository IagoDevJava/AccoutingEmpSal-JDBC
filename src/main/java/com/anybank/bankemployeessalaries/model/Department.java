package com.anybank.bankemployeessalaries.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
@Builder
public class Department {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private Employee head;
}
