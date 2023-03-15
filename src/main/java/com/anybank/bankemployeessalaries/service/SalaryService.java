package com.anybank.bankemployeessalaries.service;

import com.anybank.bankemployeessalaries.dao.SalaryDao;
import com.anybank.bankemployeessalaries.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryService {
    private final SalaryDao salaryDao;

    @Autowired
    public SalaryService(SalaryDao salaryDao) {
        this.salaryDao = salaryDao;
    }

    /**
     * получить данные о зарплате по сотруднику за месяц
     */
    public Salary getSalaryByMonthForEmployee(Integer employeeId, String month, String year) {
        return salaryDao.getSalaryByMonthForEmployee(employeeId, month, year);
    }

    /**
     * получить данные о зарплате по сотруднику за год
     */
    public List<Salary> getSalaryByYearForEmployee(Integer employeeId, String year) {
        return salaryDao.getSalaryByYearForEmployee(employeeId, year);
    }

    /**
     * получить данные о зарплате по отделу за месяц
     */
    public List<Salary> getSalaryByMonthForDepartment(Integer departmentId, String month, String year) {
        return salaryDao.getSalaryByMonthForDepartment(departmentId, month, year);
    }

    /**
     * получить данные о зарплате по отделу за год
     */
    public List<Salary> getSalaryByYearForDepartment(Integer departmentId, String year) {
        return salaryDao.getSalaryByYearForDepartment(departmentId, year);
    }

    /**
     * получить данные о зарплате по компании за месяц
     */
    public List<Salary> getSalaryByMonth(String month, String year) {
        return salaryDao.getSalaryByMonth(month, year);
    }

    /**
     * получить данные о зарплате по компании за год
     */
    public List<Salary> getSalaryByYear(String year) {
        return salaryDao.getSalaryByYear(year);
    }

    /**
     * Рассчитать данные о зарплате по сотруднику за месяц
     */
    public Salary calculateSalaryByMonthForEmployee(Integer employeeId, Integer countDays, String end) {
        return salaryDao.calculateSalaryByMonthForEmployee(employeeId, countDays, end);
    }
}
