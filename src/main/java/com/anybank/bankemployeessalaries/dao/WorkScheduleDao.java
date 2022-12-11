package com.anybank.bankemployeessalaries.dao;

import com.anybank.bankemployeessalaries.model.WorkSchedule;

import java.util.List;

public interface WorkScheduleDao {

    /**
     * Добавить график в БД
     */
    WorkSchedule addSchedule(WorkSchedule workSchedule);

    /**
     * Изменить график в БД
     */
    WorkSchedule updateSchedule(WorkSchedule workSchedule);

    /**
     * Удалить графики из БД
     */
    void deleteSchedules();

    /**
     * Удалить график в БД по id
     */
    void deleteScheduleById(int id);

    /**
     * Получить все графики в БД
     */
    List<WorkSchedule> getSchedules();

    /**
     * Получить график в БД по id
     */
    WorkSchedule getScheduleById(int id);
}
