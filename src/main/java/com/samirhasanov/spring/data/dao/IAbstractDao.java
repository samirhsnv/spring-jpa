/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.dao;

import java.util.List;

/**
 *
 * @author Hasanov (Asus)
 */
public interface IAbstractDao<T> {
    public List<T> findAll();
    public T findById(Long id);
    public void save(T t);
    public void delete(T t);
}
