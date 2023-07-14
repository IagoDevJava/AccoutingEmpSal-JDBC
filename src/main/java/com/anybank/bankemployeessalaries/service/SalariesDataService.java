//package com.anybank.bankemployeessalaries.service;
//
//import com.anybank.bankemployeessalaries.dao.SalariesDataDao;
//import com.anybank.bankemployeessalaries.model.SalariesData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class SalariesDataService {
//    private final SalariesDataDao salariesDataDao;
//
//    @Autowired
//    public SalariesDataService(SalariesDataDao salariesDataDao) {
//        this.salariesDataDao = salariesDataDao;
//    }
//
//    /**
//     * Добавить данные о зарплате в БД
//     */
//    public SalariesData addSalariesData(SalariesData salariesData) {
//        return salariesDataDao.addSalariesData(salariesData);
//    }
//
//    /**
//     * Заменить данные о зарплате в БД
//     */
//    public SalariesData updateSalariesData(SalariesData salariesData) {
//        return salariesDataDao.updateSalariesData(salariesData);
//    }
//
//    /**
//     * Удалить все данные зарплат из БД
//     */
//    public void deleteSalariesData() {
//        salariesDataDao.deleteSalariesData();
//    }
//
//    /**
//     * Удалить данные зарплаты в БД по id
//     */
//    public void deleteSalariesDataById(Integer id) {
//        salariesDataDao.deleteSalariesDataById(id);
//    }
//
//    /**
//     * Получить все данные зарплат в БД
//     */
//    public List<SalariesData> getSalariesData() {
//        return salariesDataDao.getSalariesData();
//    }
//
//    /**
//     * Получить данные зарплаты в БД по id
//     */
//    public SalariesData getSalariesDataById(Integer id) {
//        return salariesDataDao.getSalariesDataById(id);
//    }
//}
