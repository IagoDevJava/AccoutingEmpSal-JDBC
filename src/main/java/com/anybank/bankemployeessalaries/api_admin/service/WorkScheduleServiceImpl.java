package com.anybank.bankemployeessalaries.api_admin.service;

import com.anybank.bankemployeessalaries.dto.WorkScheduleDto;
import com.anybank.bankemployeessalaries.exception.ScheduleNotFoundException;
import com.anybank.bankemployeessalaries.mapper.WorkScheduleMapper;
import com.anybank.bankemployeessalaries.model.WorkSchedule;
import com.anybank.bankemployeessalaries.repository.WorkScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkScheduleServiceImpl implements WorkScheduleService {
    private final WorkScheduleRepository workScheduleRepository;

    /**
     * Добавить график в БД
     */
    @Override
    public WorkScheduleDto addSchedule(WorkSchedule workSchedule) {
        return WorkScheduleMapper.toWorkScheduleDto(workScheduleRepository.save(workSchedule));
    }

    /**
     * Заменить график в БД
     */
    @Override
    public WorkScheduleDto updateSchedule(WorkSchedule workSchedule, Integer scheduleId) {
        WorkSchedule scheduleById = workScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found"));

        scheduleById.setId(workSchedule.getId());
        scheduleById.setWorkDay(workSchedule.getWorkDay());
        scheduleById.setWeekDay(workSchedule.getWeekDay());
        scheduleById.setWorkHour(workSchedule.getWorkHour());

        return WorkScheduleMapper.toWorkScheduleDto(scheduleById);
    }

    /**
     * Удалить графики из БД
     */
    @Override
    public void deleteSchedules() {
        workScheduleRepository.deleteAll();
    }

    /**
     * Удалить график в БД по id
     */
    @Override
    public void deleteScheduleById(Integer scheduleId) {
        workScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found"));
        workScheduleRepository.deleteById(scheduleId);
    }

    /**
     * Получить все графики в БД
     */
    @Override
    public List<WorkScheduleDto> getSchedules() {
        return WorkScheduleMapper.toWorkScheduleDtoList(workScheduleRepository.findAll());
    }

    /**
     * Получить график в БД по id
     */
    @Override
    public WorkScheduleDto getScheduleById(Integer scheduleId) {
        return WorkScheduleMapper.toWorkScheduleDto(workScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found")));
    }
}
