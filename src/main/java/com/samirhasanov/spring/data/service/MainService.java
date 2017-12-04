/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.service;

import com.samirhasanov.spring.data.dao.IEmployeeDao;
import com.samirhasanov.spring.data.dao.IOrganizationDao;
import com.samirhasanov.spring.data.domain.Employee;
import com.samirhasanov.spring.data.domain.Organization;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hasanov (Asus)
 */
@Service
@Transactional
public class MainService implements IMainService {
    private final IOrganizationDao organizationDao;
    private final IEmployeeDao employeeDao;
    
    @Autowired
    public MainService(IOrganizationDao organizationDao, IEmployeeDao employeeDao) {
        this.organizationDao = organizationDao;
        this.employeeDao = employeeDao;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Organization> findAllOrganizations() {
        return this.organizationDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Organization findOrganizationById(Long id) {
        return this.organizationDao.findById(id);
    }

    @Override
    public void saveOrganization(Organization organization) {
        this.organizationDao.save(organization);
    }

    @Override
    public void deleteOrganization(Organization t) {
        this.organizationDao.delete(t);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> findAllOrganizationsByType(String type) {
        return this.organizationDao.findAllByType(type);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAllEmployeesByFullname(String fullname) {
        return this.employeeDao.findAllByFullname(fullname);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAllEmployeesByPosition(String position) {
        return this.employeeDao.findAllByPosition(position);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAllEmployeesByOrganization(Organization organization) {
        return this.employeeDao.findAllByOrganization(organization);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAllEmployees() {
        return this.employeeDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findEmployeeById(Long id) {
        return this.employeeDao.findById(id);
    }

    @Override
    public void saveEmployee(Employee t) {
        this.employeeDao.save(t);
    }

    @Override
    public void deleteEmployee(Employee t) {
        this.employeeDao.delete(t);
    }
    
}
