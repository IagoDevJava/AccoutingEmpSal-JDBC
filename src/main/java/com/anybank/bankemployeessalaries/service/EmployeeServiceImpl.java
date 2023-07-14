package com.anybank.bankemployeessalaries.service;

import com.anybank.bankemployeessalaries.dto.EmployeeDto;
import com.anybank.bankemployeessalaries.enum_model.JobStatus;
import com.anybank.bankemployeessalaries.exception.EmployeeNotFoundException;
import com.anybank.bankemployeessalaries.mapper.EmployeeMapper;
import com.anybank.bankemployeessalaries.model.Employee;
import com.anybank.bankemployeessalaries.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;


    /**
     * Добавление сотрудника в БД
     */
    @Override
    public EmployeeDto addEmployee(Employee employee) {
        employee.setJobStatus(JobStatus.NEW);
        return EmployeeMapper.toEmployeeDto(employeeRepository.save(employee));
    }

    /**
     * Обновление сотрудника в БД
     */
    @Override
    public EmployeeDto updateEmployee(Employee employee, Integer employeeId) {
        Employee employeeById = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        employeeById.setId(employee.getId());
        employeeById.setSurname(employee.getSurname());
        employeeById.setFirstname(employee.getFirstname());
        employeeById.setLastname(employee.getLastname());
        employeeById.setGender(employee.getGender());
        employeeById.setDepartment(employee.getDepartment());
        employeeById.setPhone(employee.getPhone());
        employeeById.setEmail(employee.getEmail());
        employeeById.setPositionId(employee.getPositionId());
        employeeById.setWorkScheduleId(employee.getWorkScheduleId());
        employeeById.setDateOfAdmission(employee.getDateOfAdmission());
        employeeById.setDateOfDismissal(employee.getDateOfDismissal());
        employeeById.setJobStatus(employee.getJobStatus());

        return EmployeeMapper.toEmployeeDto(employeeRepository.save(employeeById));
    }

    /**
     * Удаление всех сотрудников из БД
     */
    @Override
    public void deleteEmployees() {
        employeeRepository.deleteAll();
    }

    /**
     * Удаление сотрудника по id из БД
     */
    @Override
    public void deleteEmployeeById(Integer employeeId) {
        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        employeeRepository.deleteById(employeeId);
    }

    /**
     * Получение списка сотрудников из БД
     */
    @Override
    public List<EmployeeDto> getEmployees() {
        return EmployeeMapper.toEmployeeDtoList(employeeRepository.findAll());
    }

    /**
     * Получение сотрудника по id
     */
    @Override
    public EmployeeDto getEmployeeById(Integer employeeId) {
        return EmployeeMapper.toEmployeeDto(employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found")));
    }
}
