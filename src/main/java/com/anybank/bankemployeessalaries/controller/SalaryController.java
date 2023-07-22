package com.anybank.bankemployeessalaries.controller;

import com.anybank.bankemployeessalaries.dto.SalaryDto;
import com.anybank.bankemployeessalaries.service.SalaryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@Transactional(isolation = Isolation.READ_COMMITTED)
@RestController
@AllArgsConstructor
@RequestMapping("/salaries")
public class SalaryController {
    private final SalaryService salaryService;

    /**
     * Сохранить данные о зарплате по сотруднику за месяц
     */
    @Transactional
    @PostMapping("/{employeeId}")
    public ResponseEntity<SalaryDto> addSalaryByPeriodForEmployee(
            @PathVariable @PositiveOrZero Integer employeeId,
            @RequestParam String month,
            @RequestParam String year
    ) {
        return ResponseEntity.ok(salaryService.addSalaryByPeriodForEmployee(employeeId, month, year));
    }

//    /**
//     * Рассчитать данные о зарплате по сотруднику за месяц
//     */
//    @Transactional
//    @PostMapping("/calculate/{employeeId}")
//    public ResponseEntity<SalaryDto> calculateSalaryByPeriodForEmployee(
//            @PathVariable @PositiveOrZero Integer employeeId,
//            @RequestParam Integer countWorkDays,
//            @RequestParam String year
//    ) {
//        return ResponseEntity.ok(salaryService.calculateSalaryByPeriodForEmployee(employeeId, countWorkDays, year));
//    }


    /**
     * получить данные о зарплате по сотруднику за месяц
     */
    @Transactional(readOnly = true)
    @GetMapping("/{employeeId}/month")
    public ResponseEntity<SalaryDto> getSalaryByMonthForEmployee(@PositiveOrZero @PathVariable Integer employeeId,
                                                                 @RequestParam String month,
                                                                 @RequestParam String year) {
        return ResponseEntity.ok(salaryService.getSalaryByMonthForEmployee(employeeId, month, year));
    }

    /**
     * получить данные о зарплате по сотруднику за год
     */
    @Transactional(readOnly = true)
    @GetMapping("/{employeeId}/year")
    public ResponseEntity<List<SalaryDto>> getSalaryByYearForEmployee(@PositiveOrZero @PathVariable Integer employeeId,
                                                                      @RequestParam String year) {
        return ResponseEntity.ok(salaryService.getSalaryByYearForEmployee(employeeId, year));
    }

    /**
     * получить данные о зарплате по отделу за месяц
     */
    @Transactional(readOnly = true)
    @GetMapping("/{departmentId}/month")
    public ResponseEntity<List<SalaryDto>> getSalaryByMonthForDepartment(
            @PositiveOrZero @PathVariable Integer departmentId,
            @RequestParam String month,
            @RequestParam String year) {
        return ResponseEntity.ok(salaryService.getSalaryByMonthForDepartment(departmentId, month, year));
    }

    /**
     * получить данные о зарплате по отделу за год
     */
    @Transactional(readOnly = true)
    @GetMapping("/{departmentId}/year")
    public ResponseEntity<List<SalaryDto>> getSalaryByYearForDepartment(@PositiveOrZero @PathVariable Integer departmentId,
                                                                        @RequestParam @DateTimeFormat String year) {
        return ResponseEntity.ok(salaryService.getSalaryByYearForDepartment(departmentId, year));
    }

    /**
     * получить данные о зарплате по компании за месяц
     */
    @Transactional(readOnly = true)
    @GetMapping("/month")
    public ResponseEntity<List<SalaryDto>> getSalaryByMonth(@RequestParam String month,
                                                            @RequestParam String year) {
        return ResponseEntity.ok(salaryService.getSalaryByMonth(month, year));
    }

    /**
     * получить данные о зарплате по компании за год
     */
    @Transactional(readOnly = true)
    @GetMapping("/year")
    public ResponseEntity<List<SalaryDto>> getSalaryByYear(@RequestParam String year) {
        return ResponseEntity.ok(salaryService.getSalaryByYear(year));
    }
}
