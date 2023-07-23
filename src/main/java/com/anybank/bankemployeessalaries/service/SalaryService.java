package com.anybank.bankemployeessalaries.service;

import com.anybank.bankemployeessalaries.dto.SalaryDto;
import com.anybank.bankemployeessalaries.model.Salary;

import java.util.List;

public interface SalaryService {
    /**
     * Сохранить данные о зарплате
     */
    SalaryDto addSalary(Salary salary);

    /**
     * Обновление данные о зарплате
     */
    SalaryDto updateSalary(Salary salary, Long id);

    /**
     * Удаление всех зарплат из БД
     */
    void deleteSalary();

    /**
     * Удаление зарплат по id из БД
     */
    void deleteSalaryById(Long id);

    /**
     * получить данные о зарплате по id
     */
    SalaryDto getSalaryById(Long id);

    /**
     * получить данные о зарплате по сотруднику за месяц
     */
    SalaryDto getSalaryByMonthForEmployee(Integer employeeId, String month, String year);

    /**
     * получить данные о зарплате по сотруднику за год
     */
    List<SalaryDto> getSalaryByYearForEmployee(Integer employeeId, String year);

    /**
     * получить данные о зарплате по отделу за месяц
     */
    List<SalaryDto> getSalaryByMonthForDepartment(Integer departmentId, String month, String year);

    /**
     * получить данные о зарплате по отделу за год
     */
    List<SalaryDto> getSalaryByYearForDepartment(Integer departmentId, String year);

    /**
     * получить данные о зарплате по компании за месяц
     */
    List<SalaryDto> getSalaryByMonth(String month, String year);

    /**
     * получить данные о зарплате по компании за год
     */
    List<SalaryDto> getSalaryByYear(String year);

    /**
     * Рассчитать данные о зарплате по сотруднику за месяц
     */
    SalaryDto calculateSalaryByMonthForEmployee(Integer employeeId, Integer countWorkDays, String requireNonNullElse);
}
