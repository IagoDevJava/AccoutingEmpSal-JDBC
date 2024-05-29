package com.anybank.bankemployeessalaries.service.impl;

import com.anybank.bankemployeessalaries.dto.SalariesDataDto;
import com.anybank.bankemployeessalaries.exception.SalariesDataNotFoundException;
import com.anybank.bankemployeessalaries.mapper.SalariesDataMapper;
import com.anybank.bankemployeessalaries.model.SalariesData;
import com.anybank.bankemployeessalaries.repository.SalariesDateRepository;
import com.anybank.bankemployeessalaries.service.SalariesDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SalariesDataServiceImpl implements SalariesDataService {
    private final SalariesDateRepository salariesDateRepository;

    /**
     * Добавить данные о зарплате в БД
     */
    @Override
    public SalariesDataDto addSalariesData(SalariesData salariesData) {
        return SalariesDataMapper.toSalariesDataDto(salariesDateRepository.save(salariesData));
    }

    /**
     * Заменить данные о зарплате в БД
     */
    @Override
    public SalariesDataDto updateSalariesData(SalariesData salariesData, Integer id) {
        SalariesData salariesDataById = salariesDateRepository.findById(id)
                .orElseThrow(() -> new SalariesDataNotFoundException("SalariesData not found"));

        salariesDataById.setId(id);
        salariesDataById.setWage(salariesData.getWage());
        salariesDataById.setBonus(salariesData.getBonus());
        salariesDataById.setPosition(salariesData.getPosition());

        return SalariesDataMapper.toSalariesDataDto(salariesDateRepository.save(salariesDataById));
    }

    /**
     * Удалить все данные зарплат из БД
     */
    @Override
    public void deleteSalariesData() {
        salariesDateRepository.deleteAll();
    }

    /**
     * Удалить данные зарплаты в БД по id
     */
    @Override
    public void deleteSalariesDataById(Integer id) {
        salariesDateRepository.deleteById(id);
    }

    /**
     * Получить все данные зарплат в БД
     */
    @Override
    public List<SalariesDataDto> getSalariesData() {
        return SalariesDataMapper.toGSalariesDataDtoList(salariesDateRepository.findAll());
    }

    /**
     * Получить данные зарплаты в БД по id
     */
    @Override
    public SalariesDataDto getSalariesDataById(Integer id) {
        return SalariesDataMapper.toSalariesDataDto(salariesDateRepository.findById(id)
                .orElseThrow(() -> new SalariesDataNotFoundException("SalariesData not found")));
    }
}
