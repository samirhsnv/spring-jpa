/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.service;

import com.samirhasanov.spring.data.domain.Employee;
import com.samirhasanov.spring.data.domain.Organization;
import java.util.List;

/**
 *
 * @author Hasanov (Asus)
 */
public interface IMainService {
    public List<Organization> findAllOrganizations();
    public Organization findOrganizationById(Long id);
    public void saveOrganization(Organization organization);
    public void deleteOrganization(Organization t);
    public List<Organization> findAllOrganizationsByType(String type);
    public List<Employee> findAllEmployeesByFullname(String fullname);
    public List<Employee> findAllEmployeesByPosition(String position);
    public List<Employee> findAllEmployeesByOrganization(Organization organization);
    public List<Employee> findAllEmployees();
    public Employee findEmployeeById(Long id);
    public void saveEmployee(Employee t);
    public void deleteEmployee(Employee t);
}
