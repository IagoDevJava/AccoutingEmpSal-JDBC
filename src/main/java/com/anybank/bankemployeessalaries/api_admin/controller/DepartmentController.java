//package com.anybank.bankemployeessalaries.controller;
//
//import com.anybank.bankemployeessalaries.model.Department;
//import com.anybank.bankemployeessalaries.service.DepartmentService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import javax.validation.constraints.PositiveOrZero;
//import java.util.List;
//
//@Slf4j
//@RestController
//@Validated
//@RequestMapping("/department")
//public class DepartmentController {
//    private final DepartmentService departmentService;
//
//    public DepartmentController(DepartmentService departmentService) {
//        this.departmentService = departmentService;
//    }
//
//    /**
//     * Добавление департамента в БД
//     */
//    @PostMapping
//    public ResponseEntity<Department> addDepartment(@Valid @RequestBody Department department) {
//        log.info("Добавляем департамент № {} в БД", department.getId());
//        return ResponseEntity.ok(departmentService.addDepartment(department));
//    }
//
//    /**
//     * Обновление департамента в БД
//     */
//    @PutMapping
//    public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
//        log.info("Обновляем департамент № {} в БД", department.getId());
//        return ResponseEntity.ok(departmentService.updateDepartment(department));
//    }
//
//    /**
//     * Удаление всех департаментов из БД
//     */
//    @DeleteMapping
//    public ResponseEntity<Void> deleteDepartment() {
//        log.info("Очищаем департаменты БД");
//        departmentService.deleteDepartment();
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    /**
//     * Удаление департамента по id из БД
//     */
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteDepartmentById(@PositiveOrZero @PathVariable Integer id) {
//        log.info("Удаляем департамент № {} в БД", id);
//        departmentService.deleteDepartmentById(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    /**
//     * Получение списка департаментов из БД
//     */
//    @GetMapping
//    public ResponseEntity<List<Department>> getDepartment() {
//        log.info("Получаем список департаментов БД");
//        return ResponseEntity.ok(departmentService.getDepartment());
//    }
//
//    /**
//     * Получение департамента по id
//     */
//    @GetMapping("/{id}")
//    public ResponseEntity<Department> findDepartmentById(@PositiveOrZero @PathVariable Integer id) {
//        log.info("Получаем департамент № {} в БД", id);
//        return ResponseEntity.ok(departmentService.findDepartmentById(id));
//    }
//}
