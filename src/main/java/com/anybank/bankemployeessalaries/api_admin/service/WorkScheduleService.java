package com.anybank.bankemployeessalaries.api_admin.service;

import com.anybank.bankemployeessalaries.dto.WorkScheduleDto;
import com.anybank.bankemployeessalaries.model.WorkSchedule;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;


@Validated
public interface WorkScheduleService {
    /**
     * Добавить график в БД
     */
    WorkScheduleDto addSchedule(@Valid WorkSchedule workSchedule);


    /**
     * Изменить график в БД
     */
    WorkScheduleDto updateSchedule(WorkSchedule workSchedule, @PositiveOrZero Integer scheduleId);

    /**
     * Удалить графики из БД
     */
    void deleteSchedules();

    /**
     * Удалить график в БД по id
     */
    void deleteScheduleById(@PositiveOrZero Integer scheduleId);

    /**
     * Получить все графики в БД
     */
    List<WorkScheduleDto> getSchedules();

    /**
     * Получить график в БД по id
     */
    WorkScheduleDto getScheduleById(@PositiveOrZero Integer scheduleId);
}
