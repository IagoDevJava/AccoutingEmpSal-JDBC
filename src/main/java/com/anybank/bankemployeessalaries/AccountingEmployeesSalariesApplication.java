package com.anybank.bankemployeessalaries;

import com.anybank.bankemployeessalaries.check.CheckDataOfEmployees;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountingEmployeesSalariesApplication {

    public static void main(String[] args) {
//        CheckDataOfEmployees.checkData();
        SpringApplication.run(AccountingEmployeesSalariesApplication.class, args);
    }

}
