package com.anybank.service.impl;

import com.anybank.dto.WorkScheduleDto;
import com.anybank.exception.ScheduleNotFoundException;
import com.anybank.mapper.WorkScheduleMapper;
import com.anybank.model.WorkSchedule;
import com.anybank.repository.WorkScheduleRepository;
import com.anybank.service.WorkScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class WorkScheduleServiceImpl implements WorkScheduleService {
    private final WorkScheduleRepository workScheduleRepository;

    /**
     * Добавить график в БД
     */
    @Override
    @Transactional
    public WorkScheduleDto addSchedule(WorkSchedule workSchedule) {
        return WorkScheduleMapper.toWorkScheduleDto(workScheduleRepository.save(workSchedule));
    }

    /**
     * Заменить график в БД
     */
    @Override
    @Transactional
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
    @Transactional
    public void deleteSchedules() {
        workScheduleRepository.deleteAll();
    }

    /**
     * Удалить график в БД по id
     */
    @Override
    @Transactional
    public void deleteScheduleById(Integer scheduleId) {
        workScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found"));
        workScheduleRepository.deleteById(scheduleId);
    }

    /**
     * Получить все графики в БД
     */
    @Override
    @Transactional
    public List<WorkScheduleDto> getSchedules() {
        return WorkScheduleMapper.toWorkScheduleDtoList(workScheduleRepository.findAll());
    }

    /**
     * Получить график в БД по id
     */
    @Override
    @Transactional
    public WorkScheduleDto getScheduleById(Integer scheduleId) {
        return WorkScheduleMapper.toWorkScheduleDto(workScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found")));
    }
}
