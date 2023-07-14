package com.anybank.bankemployeessalaries.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DepartmentDto {
    Integer id;
    String name;
    String address;
    String phone;
    String email;
    Integer headId;
}