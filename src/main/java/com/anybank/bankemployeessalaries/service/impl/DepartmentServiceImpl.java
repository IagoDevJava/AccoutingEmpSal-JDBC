package com.anybank.bankemployeessalaries.service.impl;

import com.anybank.bankemployeessalaries.dto.DepartmentDto;
import com.anybank.bankemployeessalaries.exception.DepartmentNotFoundException;
import com.anybank.bankemployeessalaries.mapper.DepartmentMapper;
import com.anybank.bankemployeessalaries.model.Department;
import com.anybank.bankemployeessalaries.repository.DepartmentRepository;
import com.anybank.bankemployeessalaries.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    /**
     * Добавление департамента в БД
     */
    @Transactional
    @Override
    public DepartmentDto addDepartment(Department department) {
        return DepartmentMapper.toDepartmentDto(departmentRepository.save(department));
    }

    /**
     * Обновление департамента в БД
     */
    @Transactional
    @Override
    public DepartmentDto updateDepartment(Department department, Integer id) {
        Department departmentById = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));

        departmentById.setId(department.getId());
        departmentById.setName(department.getName());
        departmentById.setAddress(department.getAddress());
        departmentById.setPhone(department.getPhone());
        departmentById.setEmail(department.getEmail());
        departmentById.setHead(department.getHead());

        return DepartmentMapper.toDepartmentDto(departmentRepository.save(departmentById));
    }

    /**
     * Удаление всех департаментов из БД
     */
    @Transactional
    @Override
    public void deleteDepartment() {
        departmentRepository.deleteAll();
    }

    /**
     * Удаление департамента по id из БД
     */
    @Transactional
    @Override
    public void deleteDepartmentById(Integer id) {
        departmentRepository.deleteById(id);
    }

    /**
     * Получение списка департаментов из БД
     */
    @Transactional
    @Override
    public List<DepartmentDto> getDepartment() {
        return DepartmentMapper.toDepartmentDtoList(departmentRepository.findAll());
    }

    /**
     * Получение департамента по id
     */
    @Transactional
    @Override
    public DepartmentDto findDepartmentById(Integer id) {
        return DepartmentMapper.toDepartmentDto(departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found")));
    }
}
