//package com.anybank.bankemployeessalaries.dao;
//
//import com.anybank.bankemployeessalaries.model.Salary;
//
//import java.util.List;
//
//public interface SalaryDao {
//    /**
//     * получить данные о зарплате по сотруднику за месяц
//     */
//    Salary getSalaryByMonthForEmployee(Integer employeeId, String month, String year);
//
//    /**
//     * получить данные о зарплате по сотруднику за год
//     */
//    List<Salary> getSalaryByYearForEmployee(Integer employeeId, String year);
//
//    /**
//     * получить данные о зарплате по отделу за месяц
//     */
//    List<Salary> getSalaryByMonthForDepartment(Integer departmentId, String month, String year);
//
//    /**
//     * получить данные о зарплате по отделу за год
//     */
//    List<Salary> getSalaryByYearForDepartment(Integer departmentId, String year);
//
//    /**
//     * получить данные о зарплате по компании за месяц
//     */
//    List<Salary> getSalaryByMonth(String month, String year);
//
//    /**
//     * получить данные о зарплате по компании за год
//     */
//    List<Salary> getSalaryByYear(String year);
//
//    /**
//     * Рассчитать данные о зарплате по сотруднику за месяц
//     */
//    Salary calculateSalaryByMonthForEmployee(Integer employeeId, Integer countDays, String end);
//}
