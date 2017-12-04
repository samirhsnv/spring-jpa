/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.dao;

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
public class OrganizationDao implements IOrganizationDao {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Organization> findAll() {
        return entityManager.createQuery("from Organization t").getResultList();
    }

    @Override
    public Organization findById(Long id) {
        return entityManager.find(Organization.class, id);
    }

    @Override
    public void save(Organization organization) {
        entityManager.persist(organization);
    }

    @Override
    public void delete(Organization t) {
        entityManager.remove(t);
    }

    @Override
    public List<Organization> findAllByType(String type) {
        return entityManager
                .createQuery("from Organization t where t.type like '%' || :type || '%'")
                .setParameter("type", type)
                .getResultList();
    }
    
}
