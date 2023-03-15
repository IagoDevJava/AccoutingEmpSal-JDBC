package com.anybank.bankemployeessalaries.controller;

import com.anybank.bankemployeessalaries.model.Salary;
import com.anybank.bankemployeessalaries.service.SalaryService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/salaries")
public class SalaryController {
    private final SalaryService salaryService;

    @Autowired
    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    /**
     * Рассчитать данные о зарплате по сотруднику за месяц
     */
    @PostMapping("/calculate/{employeeId}")
    public ResponseEntity<Salary> calculateSalaryByPeriodForEmployee(
            @PathVariable @PositiveOrZero Integer employeeId,
            @RequestParam @NotBlank @PositiveOrZero Integer countDays,
            @RequestParam @DateTimeFormat String end
    ) {
        return ResponseEntity.ok(salaryService.calculateSalaryByMonthForEmployee(employeeId, countDays, end));
    }


    /**
     * получить данные о зарплате по сотруднику за месяц
     */
    @GetMapping("/{employeeId}/month")
    public ResponseEntity<Salary> getSalaryByMonthForEmployee(@PositiveOrZero @PathVariable Integer employeeId,
                                                              @RequestParam String month,
                                                              @RequestParam String year) {
        return ResponseEntity.ok(salaryService.getSalaryByMonthForEmployee(employeeId, month, year));
    }

    /**
     * получить данные о зарплате по сотруднику за год
     */
    @GetMapping("/{employeeId}/year")
    public ResponseEntity<List<Salary>> getSalaryByYearForEmployee(@PositiveOrZero @PathVariable Integer employeeId,
                                                                   @RequestParam String year) {
        return ResponseEntity.ok(salaryService.getSalaryByYearForEmployee(employeeId, year));
    }

    /**
     * получить данные о зарплате по отделу за месяц
     */
    @GetMapping("/{departmentId}/month")
    public ResponseEntity<List<Salary>> getSalaryByMonthForDepartment(
            @PositiveOrZero @PathVariable Integer departmentId,
            @RequestParam String month,
            @RequestParam String year) {
        return ResponseEntity.ok(salaryService.getSalaryByMonthForDepartment(departmentId, month, year));
    }

    /**
     * получить данные о зарплате по отделу за год
     */
    @GetMapping("/{departmentId}/year")
    public ResponseEntity<List<Salary>> getSalaryByYearForDepartment(@PositiveOrZero @PathVariable Integer departmentId,
                                                                     @RequestParam @DateTimeFormat String year) {
        return ResponseEntity.ok(salaryService.getSalaryByYearForDepartment(departmentId, year));
    }

    /**
     * получить данные о зарплате по компании за месяц
     */
    @GetMapping("/month")
    public ResponseEntity<List<Salary>> getSalaryByMonth(@RequestParam String month,
                                                         @RequestParam String year) {
        return ResponseEntity.ok(salaryService.getSalaryByMonth(month, year));
    }

    /**
     * получить данные о зарплате по компании за год
     */
    @GetMapping("/year")
    public ResponseEntity<List<Salary>> getSalaryByYear(@RequestParam String year) {
        return ResponseEntity.ok(salaryService.getSalaryByYear(year));
    }
}
