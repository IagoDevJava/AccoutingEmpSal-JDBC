package com.anybank.bankemployeessalaries.service.impl;

import com.anybank.bankemployeessalaries.dto.SalaryDto;
import com.anybank.bankemployeessalaries.enum_model.JobStatus;
import com.anybank.bankemployeessalaries.exception.EmployeeNotFoundException;
import com.anybank.bankemployeessalaries.exception.KpiNotFoundException;
import com.anybank.bankemployeessalaries.exception.PositionNotFoundException;
import com.anybank.bankemployeessalaries.exception.SalaryNotFoundException;
import com.anybank.bankemployeessalaries.mapper.SalaryMapper;
import com.anybank.bankemployeessalaries.model.*;
import com.anybank.bankemployeessalaries.repository.EmployeeRepository;
import com.anybank.bankemployeessalaries.repository.KpiRepository;
import com.anybank.bankemployeessalaries.repository.SalariesDateRepository;
import com.anybank.bankemployeessalaries.repository.SalaryRepository;
import com.anybank.bankemployeessalaries.service.SalaryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SalaryServiceImpl implements SalaryService {
    private final SalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;
    private final SalariesDateRepository salariesDateRepository;
    private final KpiRepository kpiRepository;

    /**
     * Сохранить данные о зарплате
     */
    @Override
    public SalaryDto addSalary(Salary salary) {
        return SalaryMapper.toSalaryDto(salaryRepository.save(salary));
    }

    /**
     * Обновление данные о зарплате
     */
    @Override
    public SalaryDto updateSalary(Salary salary, Long id) {
        Salary salaryById = salaryRepository.findById(id)
                .orElseThrow(() -> new SalaryNotFoundException("salary not found"));

        salaryById.setId(id);
        salaryById.setEmployeeId(salary.getEmployee());
        salaryById.setDepartmentId(salary.getDepartment());
        salaryById.setMonth(salary.getMonth());
        salaryById.setYear(salary.getYear());
        salaryById.setPayment(salary.getPayment());

        return SalaryMapper.toSalaryDto(salaryRepository.save(salaryById));
    }

    /**
     * Удаление всех зарплат из БД
     */
    @Override
    public void deleteSalary() {
        salaryRepository.deleteAll();
    }

    /**
     * Удаление зарплат по id из БД
     */
    @Override
    public void deleteSalaryById(Long id) {
        salaryRepository.findById(id).orElseThrow(() -> new SalaryNotFoundException("salary not found"));
        salaryRepository.deleteById(id);
    }

    /**
     * получить данные о зарплате по id
     */
    @Override
    public SalaryDto getSalaryById(Long id) {
        return SalaryMapper.toSalaryDto(salaryRepository.findById(id)
                .orElseThrow(() -> new SalaryNotFoundException("salary not found")));
    }

    /**
     * получить данные о зарплате по сотруднику за месяц
     */
    @Override
    public SalaryDto getSalaryByMonthForEmployee(Integer employeeId, String month, String year) {
        return SalaryMapper.toSalaryDto(salaryRepository.findByEmployeeAndMonthAndYear(employeeId, month, year)
                .orElseThrow(() -> new SalaryNotFoundException("salary not found")));
    }

    /**
     * получить данные о зарплате по сотруднику за год
     */
    @Override
    public List<SalaryDto> getSalaryByYearForEmployee(Integer employeeId, String year) {
        return SalaryMapper.toSalaryDtoList(salaryRepository.findByEmployeeAndYear(employeeId, year));
    }

    /**
     * получить данные о зарплате по отделу за месяц
     */
    @Override
    public List<SalaryDto> getSalaryByMonthForDepartment(Integer departmentId, String month, String year) {

        return SalaryMapper.toSalaryDtoList(
                salaryRepository.findByDepartmentAndMonthAndYear(departmentId, month, year)
        );
    }

    /**
     * получить данные о зарплате по отделу за год
     */
    @Override
    public List<SalaryDto> getSalaryByYearForDepartment(Integer departmentId, String year) {
        return SalaryMapper.toSalaryDtoList(salaryRepository.findByDepartmentAndYear(departmentId, year));
    }

    /**
     * получить данные о зарплате по компании за месяц
     */
    @Override
    public List<SalaryDto> getSalaryByMonth(String month, String year) {
        return SalaryMapper.toSalaryDtoList(salaryRepository.findByMonthAndYear(month, year));
    }

    /**
     * получить данные о зарплате по компании за год
     */
    @Override
    public List<SalaryDto> getSalaryByYear(String year) {
        return SalaryMapper.toSalaryDtoList(salaryRepository.findByYear(year));
    }

    /**
     * Рассчитать данные о зарплате по сотруднику за месяц
     */
    @Override
    public Salary calculateSalaryByMonthForEmployee(Integer employeeId,
                                                    String month,
                                                    String year,
                                                    Integer countWorkDays,
                                                    Integer countMedDays) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        Double wageForEmpForPeriod = getWageForEmployeePeriod(employee, countWorkDays, countMedDays);
        Double bonusForEmpForPeriod = getBonusForEmployeePeriod(employee, month, year);

        return Salary.builder()
                .employee(employee)
                .month(month)
                .year(year)
                .payment(wageForEmpForPeriod + bonusForEmpForPeriod)
                .build();
    }

    /**
     * Рассчитать окладную часть
     */
    private Double getWageForEmployeePeriod(Employee employee, Integer countWorkDays, Integer countMedicalDays) {
        //Получить оклад из БД
        Position position = employee.getPosition();
        SalariesData salariesData = salariesDateRepository
                .findByPosition(position.getId()).orElseThrow(() -> new PositionNotFoundException("Position not found"));
        Double wage = salariesData.getWage();

        //Получить оклад по отработанным дням
        double paymentForWorkDays = wage / LocalDate.now().lengthOfMonth() * countWorkDays;
        //Получить оклад по больничным дням
        double paymentForMedicalDays
                = wage / LocalDate.now().lengthOfMonth() * countMedicalDays * getRatioForMedDaysEmployeePeriod(employee);

        return paymentForWorkDays + paymentForMedicalDays;
    }

    /**
     * Рассчитать бонусную часть
     */
    private Double getBonusForEmployeePeriod(Employee employee, String month, String year) {
        //найти бонусную часть
        Position position = employee.getPosition();
        SalariesData salariesData = salariesDateRepository
                .findByPosition(position.getId()).orElseThrow(() -> new PositionNotFoundException("Position not found"));
        Double bonus = salariesData.getBonus();

        //найти kpi сотрудника за месяц
        Kpi kpiById = kpiRepository.findByEmployeeAndMonthAndYear(
                employee.getId(), month, year).orElseThrow(() -> new KpiNotFoundException("Position not found"));

        return bonus -
                (bonus * kpiById.getPersonalKpi()
                        + bonus * kpiById.getTeamKpi()
                        + bonus * kpiById.getCommonKpi());
    }

    /**
     * Рассчитать коэффициент больничной выплаты TODO Уточнить данные у отдела кадров
     */
    private Double getRatioForMedDaysEmployeePeriod(Employee employee) {
        double ratio = 0.0;
        if (employee.getJobStatus().equals(JobStatus.WORKING)) {
            LocalDateTime dateOfAdmission = employee.getDateOfAdmission();
            if (LocalDateTime.now().isAfter(dateOfAdmission.plusYears(2L))) {
                ratio = 1.0;
            } else if (LocalDateTime.now().isAfter(dateOfAdmission.plusYears(1L).plusMonths(6L))) {
                ratio = 0.8;
            } else if (LocalDateTime.now().isAfter(dateOfAdmission.plusYears(1L))) {
                ratio = 0.6;
            } else if (LocalDateTime.now().isAfter(dateOfAdmission.plusMonths(6L))) {
                ratio = 0.4;
            }
        }
        return ratio;
    }
}