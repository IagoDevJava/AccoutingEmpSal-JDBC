package com.anybank.bankemployeessalaries.service;

import com.anybank.bankemployeessalaries.dto.SalaryDto;
import com.anybank.bankemployeessalaries.exception.AttendanceDataNotFoundException;
import com.anybank.bankemployeessalaries.exception.EmployeeIsFiredException;
import com.anybank.bankemployeessalaries.exception.EmployeeNotFoundException;
import com.anybank.bankemployeessalaries.exception.SalaryNotFoundException;
import com.anybank.bankemployeessalaries.mapper.SalaryMapper;
import com.anybank.bankemployeessalaries.model.AttendanceData;
import com.anybank.bankemployeessalaries.model.Salary;
import com.anybank.bankemployeessalaries.repository.AttendanceDataRepository;
import com.anybank.bankemployeessalaries.repository.EmployeeRepository;
import com.anybank.bankemployeessalaries.repository.SalariesDateRepository;
import com.anybank.bankemployeessalaries.repository.SalaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SalaryServiceImpl implements SalaryService {
    private final SalaryRepository salaryRepository;
    private final AttendanceDataRepository attendanceDataRepository;
    private final EmployeeRepository employeeRepository;
    private final SalariesDateRepository salariesDateRepository;

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
        salaryById.setEmployeeId(salary.getEmployeeId());
        salaryById.setDepartmentId(salary.getDepartmentId());
        salaryById.setMonth(salary.getMonth());
        salaryById.setYear(salary.getYear());
        salaryById.setSalary(salary.getSalary());

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
        return SalaryMapper.toSalaryDto(salaryRepository.findByEmployeeIdAndMonthAndYear(employeeId, month, year)
                .orElseThrow(() -> new SalaryNotFoundException("salary not found")));
    }

    /**
     * получить данные о зарплате по сотруднику за год
     */
    @Override
    public List<SalaryDto> getSalaryByYearForEmployee(Integer employeeId, String year) {
        return SalaryMapper.toSalaryDtoList(salaryRepository.findByEmployeeIdAndYear(employeeId, year));
    }

    /**
     * получить данные о зарплате по отделу за месяц
     */
    @Override
    public List<SalaryDto> getSalaryByMonthForDepartment(Integer departmentId, String month, String year) {
        return SalaryMapper.toSalaryDtoList(
                salaryRepository.findByDepartmentIdAndMonthAndYear(departmentId, month, year)
        );
    }

    /**
     * получить данные о зарплате по отделу за год
     */
    @Override
    public List<SalaryDto> getSalaryByYearForDepartment(Integer departmentId, String year) {
        return SalaryMapper.toSalaryDtoList(salaryRepository.findByDepartmentIdAndYear(departmentId, year));
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
    public SalaryDto calculateSalaryByMonthForEmployee(Integer employeeId,
                                                       Integer countWorkDays,
                                                       String requireNonNullElse) {
        employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("employee not found"));

        List<AttendanceData> attendanceDataByEmployeeId
                = attendanceDataRepository.findAttendanceDataByEmployeeId(employeeId);
        if (attendanceDataByEmployeeId.isEmpty()) {
            throw new AttendanceDataNotFoundException("Attendance for this department not found");
        }

        int workDaysCount = 0;
        int medicalDaysCount = 0;
        for (AttendanceData attendanceData : attendanceDataByEmployeeId) {
            switch (attendanceData.getStatus()) {
                case MEDICAL:
                    medicalDaysCount++;
                case WORKING:
                    workDaysCount++;
            }
        }

        salariesDateRepository.fi

        return null;
    }

    /**
     * Определить количество дней по статусам*/
    private
}