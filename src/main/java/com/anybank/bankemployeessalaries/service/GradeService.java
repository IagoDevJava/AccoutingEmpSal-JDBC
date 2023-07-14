package com.anybank.bankemployeessalaries.service;

import com.anybank.bankemployeessalaries.dto.GradeDto;
import com.anybank.bankemployeessalaries.model.Grade;

import java.util.List;

public interface GradeService {

    /**
     * Добавление грейда в БД
     */
    GradeDto addGrade(Grade grade);

    /**
     * Обновление грейда в БД
     */
    GradeDto updateGrade(Grade grade, Integer id);

    /**
     * Удаление всех грейдов из БД
     */
    void deleteGrades();

    /**
     * Удаление грейда по id из БД
     */
    void deleteGradeById(Integer id);

    /**
     * Получение списка грейдов из БД
     */
    List<GradeDto> getGrades();

    /**
     * Получение грейда по id
     */
    GradeDto getGradeById(int id);
}
