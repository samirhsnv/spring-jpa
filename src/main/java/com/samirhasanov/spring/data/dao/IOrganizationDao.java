/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.dao;

import com.samirhasanov.spring.data.domain.Organization;
import java.util.List;

/**
 *
 * @author Hasanov (Asus)
 */
public interface IOrganizationDao extends IAbstractDao<Organization> {
    public List<Organization> findAllByType(String type);
}
