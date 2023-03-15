package com.anybank.bankemployeessalaries.dao;

import com.anybank.bankemployeessalaries.model.SalariesData;

import java.util.List;

public interface SalariesDataDao {
    /**
     * Добавить данные о зарплате в БД
     */
    SalariesData addSalariesData(SalariesData salariesData);

    /**
     * Заменить данные о зарплате в БД
     */
    SalariesData updateSalariesData(SalariesData salariesData);

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
    List<SalariesData> getSalariesData();

    /**
     * Получить данные зарплаты в БД по id
     */
    SalariesData getSalariesDataById(Integer id);

    /**
     * Обновить значения KPI
     */
    SalariesData updateKpiSalariesData(SalariesData salariesData);
}
