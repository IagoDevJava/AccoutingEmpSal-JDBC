package com.anybank.bankemployeessalaries.service;

import com.anybank.bankemployeessalaries.dto.SalaryDto;

import java.util.List;

public interface SalaryService {
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
     * Сохранить данные о зарплате по сотруднику за месяц
     */
    SalaryDto addSalaryByPeriodForEmployee(Integer employeeId, String month, String year);
}
