//package com.anybank.bankemployeessalaries.service;
//
//import com.anybank.bankemployeessalaries.dao.GradeDao;
//import com.anybank.bankemployeessalaries.model.Grade;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.validation.constraints.PositiveOrZero;
//import java.util.List;
//
//@Service
//@Slf4j
//public class GradeService {
//    private final GradeDao gradeDao;
//
//    @Autowired
//    public GradeService(GradeDao gradeDao) {
//        this.gradeDao = gradeDao;
//    }
//
//    /**
//     * Добавление грейда в БД
//     */
//    public Grade addGrade(Grade grade) {
//        return gradeDao.addGrade(grade);
//    }
//
//    /**
//     * Обновление грейда в БД
//     */
//    public Grade updateGrade(Grade grade) {
//        return gradeDao.updateGrade(grade);
//    }
//
//    /**
//     * Удаление всех грейдов из БД
//     */
//    public void deleteGrades() {
//        gradeDao.deleteGrades();
//    }
//
//    /**
//     * Удаление грейда по id из БД
//     */
//    public void deleteGradeById(@PositiveOrZero Integer id) {
//        gradeDao.deleteGradeById(id);
//    }
//
//    /**
//     * Получение списка грейдов из БД
//     */
//    public List<Grade> getGrades() {
//        return gradeDao.getGrades();
//    }
//
//    /**
//     * Получение грейда по id
//     */
//    public Grade getGradeById(int id) {
//        return gradeDao.getGradeById(id);
//    }
//}
