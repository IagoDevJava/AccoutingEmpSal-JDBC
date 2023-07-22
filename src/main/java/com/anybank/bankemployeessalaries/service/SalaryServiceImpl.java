package com.anybank.bankemployeessalaries.service;

import com.anybank.bankemployeessalaries.dto.SalaryDto;
import com.anybank.bankemployeessalaries.repository.SalaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SalaryServiceImpl implements SalaryService {
    private final SalaryRepository salaryRepository;

    @Override
    public SalaryDto getSalaryByMonthForEmployee(Integer employeeId, String month, String year) {
        return null;
    }

    @Override
    public List<SalaryDto> getSalaryByYearForEmployee(Integer employeeId, String year) {
        return null;
    }

    @Override
    public List<SalaryDto> getSalaryByMonthForDepartment(Integer departmentId, String month, String year) {
        return null;
    }

    @Override
    public List<SalaryDto> getSalaryByYearForDepartment(Integer departmentId, String year) {
        return null;
    }

    @Override
    public List<SalaryDto> getSalaryByMonth(String month, String year) {
        return null;
    }

    @Override
    public List<SalaryDto> getSalaryByYear(String year) {
        return null;
    }

    @Override
    public SalaryDto addSalaryByPeriodForEmployee(Integer employeeId, String month, String year) {
        return null;
    }
}
