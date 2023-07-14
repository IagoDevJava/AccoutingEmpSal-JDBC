package com.anybank.bankemployeessalaries.service;

import com.anybank.bankemployeessalaries.dto.SalariesDataDto;
import com.anybank.bankemployeessalaries.model.SalariesData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SalariesDataService {
    /**
     * Добавить данные о зарплате в БД
     */
    SalariesDataDto addSalariesData(SalariesData salariesData);

    /**
     * Заменить данные о зарплате в БД
     */
    SalariesDataDto updateSalariesData(SalariesData salariesData, Integer id);

    /**
     * Удалить все данные зарплат из БД
     */
    void deleteSalariesData();

    /**
     * Удалить данные зарплаты в БД по id
     */
    void deleteSalariesDataById(Integer id);

    /**
     * Получить все данные зарплат в БД
     */
    List<SalariesDataDto> getSalariesData();

    /**
     * Получить данные зарплаты в БД по id
     */
    SalariesDataDto getSalariesDataById(Integer id);
}
