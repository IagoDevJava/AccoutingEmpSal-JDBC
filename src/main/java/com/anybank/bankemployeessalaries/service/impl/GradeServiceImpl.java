package com.anybank.bankemployeessalaries.service.impl;

import com.anybank.bankemployeessalaries.dto.GradeDto;
import com.anybank.bankemployeessalaries.exception.GradeNotFoundException;
import com.anybank.bankemployeessalaries.mapper.GradeMapper;
import com.anybank.bankemployeessalaries.model.Grade;
import com.anybank.bankemployeessalaries.repository.GradeRepository;
import com.anybank.bankemployeessalaries.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;

    /**
     * Добавление грейда в БД
     */
    @Override
    public GradeDto addGrade(Grade grade) {
        return GradeMapper.toGradeDto(gradeRepository.save(grade));
    }

    /**
     * Обновление грейда в БД
     */
    @Override
    public GradeDto updateGrade(Grade grade, Integer id) {
        Grade gradeById = gradeRepository.findById(id).orElseThrow(() -> new GradeNotFoundException("Grade not found"));

        gradeById.setId(grade.getId());
        gradeById.setName(grade.getName());

        return GradeMapper.toGradeDto(gradeRepository.save(gradeById));
    }

    /**
     * Удаление всех грейдов из БД
     */
    @Override
    public void deleteGrades() {
        gradeRepository.deleteAll();
    }

    /**
     * Удаление грейда по id из БД
     */
    @Override
    public void deleteGradeById(Integer id) {
        gradeRepository.deleteById(id);
    }

    /**
     * Получение списка грейдов из БД
     */
    @Override
    public List<GradeDto> getGrades() {
        return GradeMapper.toGradeDtoList(gradeRepository.findAll());
    }

    /**
     * Получение грейда по id
     */
    @Override
    public GradeDto getGradeById(int id) {
        return GradeMapper.toGradeDto(
                gradeRepository.findById(id).orElseThrow(() -> new GradeNotFoundException("Grade not found")));
    }
}
