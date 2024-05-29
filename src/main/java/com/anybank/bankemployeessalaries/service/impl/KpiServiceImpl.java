package com.anybank.bankemployeessalaries.service.impl;

import com.anybank.bankemployeessalaries.dto.KpiDto;
import com.anybank.bankemployeessalaries.exception.KpiNotFoundException;
import com.anybank.bankemployeessalaries.mapper.KpiMapper;
import com.anybank.bankemployeessalaries.model.Kpi;
import com.anybank.bankemployeessalaries.repository.KpiRepository;
import com.anybank.bankemployeessalaries.service.KpiService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KpiServiceImpl implements KpiService {
    private final KpiRepository kpiRepository;

    /**
     * Добавление kpi в БД
     */
    @Override
    public KpiDto addKpi(Kpi kpi) {
        return KpiMapper.toKpiDto(kpiRepository.save(kpi));
    }

    /**
     * Обновление kpi в БД
     */
    @Override
    public KpiDto updateKpi(Kpi kpi, Long id) {
        Kpi kpiById = kpiRepository.findById(id).orElseThrow(() -> new KpiNotFoundException("Kpi not found"));

        kpiById.setId(id);
        kpiById.setEmployeeId(kpi.getEmployee());
        kpiById.setPersonalKpi(kpi.getPersonalKpi());
        kpiById.setTeamKpi(kpi.getTeamKpi());
        kpiById.setCommonKpi(kpi.getCommonKpi());
        kpiById.setMonth(kpi.getMonth());
        kpiById.setYear(kpi.getYear());

        return KpiMapper.toKpiDto(kpiRepository.save(kpiById));
    }

    /**
     * Удаление всех kpi из БД
     */
    @Override
    public void deleteKpis() {
        kpiRepository.deleteAll();
    }

    /**
     * Удаление kpi по id из БД
     */
    @Override
    public void deleteKpiById(Long id) {
        kpiRepository.findById(id).orElseThrow(() -> new KpiNotFoundException("Kpi not found"));
        kpiRepository.deleteById(id);
    }

    /**
     * Получение списка kpi из БД
     */
    @Override
    public List<KpiDto> getKpis() {
        return KpiMapper.toKpiDtoList(kpiRepository.findAll());
    }

    /**
     * Получение kpi по id
     */
    @Override
    public KpiDto getKpiById(Long id) {
        return KpiMapper.toKpiDto(kpiRepository.findById(id)
                .orElseThrow(() -> new KpiNotFoundException("Kpi not found")));
    }
}
