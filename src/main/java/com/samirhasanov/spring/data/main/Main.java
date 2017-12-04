/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.main;

import com.samirhasanov.spring.data.config.AppConfig;
import com.samirhasanov.spring.data.config.DbConfig;
import com.samirhasanov.spring.data.domain.Employee;
import com.samirhasanov.spring.data.domain.Organization;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.samirhasanov.spring.data.service.IMainService;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Hasanov (Asus)
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class, DbConfig.class);
        IMainService service = applicationContext.getBean(IMainService.class);
        
        Arrays.asList(new Organization[] {
            new Organization("PASHA Bank OJSC", "Banking"),
            new Organization("Rabitabank", "Banking"),
            new Organization("Bank of Baku OJSC", "Banking"),
            new Organization("Neuron Technologies", "Software Development")
        }).forEach((org) -> {
            service.saveOrganization(org);
        });

        List<Organization> organizationsAll = service.findAllOrganizations();
        List<Organization> organizationsBanks = service.findAllOrganizationsByType("Bank");
        
        System.out.println("All: " + organizationsAll);
        System.out.println("Banks: " + organizationsBanks);
        
        Organization pshb = service.findOrganizationById(1L);
        
        Employee[] employees = {
            new Employee("Samir Hasanov", "Software engineer", pshb),
            new Employee("Irkan Ahmadov", "Senior software engineer", pshb)
        };
        
        Arrays.asList(employees).forEach((employee) -> {
            service.saveEmployee(employee);
        });
        
        List<Employee> pshbEmployees = service.findAllEmployeesByOrganization(pshb);
        System.out.println(pshbEmployees);
    }
}
