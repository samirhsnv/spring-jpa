/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.dao;

import com.samirhasanov.spring.data.domain.Employee;
import com.samirhasanov.spring.data.domain.Organization;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hasanov (Asus)
 */
@Repository
public class EmployeeDao implements IEmployeeDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Employee> findAllByFullname(String fullname) {
        return entityManager
                .createQuery("from Employee t where t.fullname like '%' || :fullname || '%'")
                .setParameter("fullname", fullname)
                .getResultList();
    }

    @Override
    public List<Employee> findAllByPosition(String position) {
        return entityManager
                .createQuery("from Employee t where t.position like '%' || :position || '%'")
                .setParameter("position", position)
                .getResultList();
    }

    @Override
    public List<Employee> findAllByOrganization(Organization organization) {
        return entityManager
                .createQuery("from Employee t where t.organization = :organization")
                .setParameter("organization", organization)
                .getResultList();
    }

    @Override
    public List<Employee> findAll() {
        return entityManager
                .createQuery("from Employee t")
                .getResultList();
    }

    @Override
    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void save(Employee t) {
        entityManager.persist(t);
    }

    @Override
    public void delete(Employee t) {
        entityManager.remove(t);
    }
    
}
