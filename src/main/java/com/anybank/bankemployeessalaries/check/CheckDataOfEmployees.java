package com.anybank.bankemployeessalaries.check;

import com.anybank.bankemployeessalaries.dto.EmployeeDto;
import com.anybank.bankemployeessalaries.enum_model.JobStatus;
import com.anybank.bankemployeessalaries.mapper.EmployeeMapper;
import com.anybank.bankemployeessalaries.model.Employee;
import com.anybank.bankemployeessalaries.service.EmployeeService;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class CheckDataOfEmployees {
    private static EmployeeService employeeService;

    public static void checkData() {
        checkStatusEmployee();


    }

    private static void checkStatusEmployee() {
        if (!employeeService.getEmployees().isEmpty()) {
            for (EmployeeDto employeeDto : employeeService.getEmployees()) {
                if (employeeDto.getDateOfAdmission().equals(LocalDateTime.now())) {
                    Employee employee = EmployeeMapper.toEmployee(employeeDto);
                    employee.setJobStatus(JobStatus.WORKING);
                    employeeService.updateEmployee(employee, employee.getId());
                }
            }
        }
    }

}
