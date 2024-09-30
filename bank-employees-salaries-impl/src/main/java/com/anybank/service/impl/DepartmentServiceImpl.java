package com.anybank.service.impl;

import com.anybank.dto.DepartmentDto;
import com.anybank.exception.DepartmentNotFoundException;
import com.anybank.mapper.DepartmentMapper;
import com.anybank.model.Department;
import com.anybank.repository.DepartmentRepository;
import com.anybank.service.DepartmentService;
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
