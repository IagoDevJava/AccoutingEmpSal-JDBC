package com.anybank.bankemployeessalaries.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkScheduleDto {
    Integer id;
    Integer workDay;
    Integer weekDay;
    Integer workHour;
}
