package com.anybank.bankemployeessalaries.dao;

import com.anybank.bankemployeessalaries.model.WorkSchedule;

public interface WorkScheduleDao {
    WorkSchedule findWorkScheduleById(int work_schedule_id);
}
