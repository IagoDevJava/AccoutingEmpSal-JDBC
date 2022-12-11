package com.anybank.bankemployeessalaries.service;

import com.anybank.bankemployeessalaries.dao.WorkScheduleDao;
import com.anybank.bankemployeessalaries.model.WorkSchedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkScheduleService {
    private final WorkScheduleDao workScheduleDao;

    public WorkScheduleService(WorkScheduleDao workScheduleDao) {
        this.workScheduleDao = workScheduleDao;
    }

    /**
     * Добавить график в БД
     */
    public WorkSchedule addSchedule(WorkSchedule workSchedule) {
        return workScheduleDao.addSchedule(workSchedule);
    }


    /**
     * Изменить график в БД
     */
    public WorkSchedule updateSchedule(WorkSchedule workSchedule) {
        return workScheduleDao.updateSchedule(workSchedule);
    }

    /**
     * Удалить графики из БД
     */
    public void deleteSchedules() {
        workScheduleDao.deleteSchedules();
    }

    /**
     * Удалить график в БД по id
     */
    public void deleteScheduleById(int id) {
        workScheduleDao.deleteScheduleById(id);
    }

    /**
     * Получить все графики в БД
     */
    public List<WorkSchedule> getSchedules() {
        return workScheduleDao.getSchedules();
    }

    /**
     * Получить график в БД по id
     */
    public WorkSchedule getScheduleById(int id) {
        return workScheduleDao.getScheduleById(id);
    }
}
