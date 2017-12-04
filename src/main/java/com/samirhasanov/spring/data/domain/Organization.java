/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Hasanov (Asus)
 */
@Entity
@Table(name = "organizations")
public class Organization extends AbstractDomain {
    
    @Column(length = 100)
    private String name;
    
    @Column(length = 50)
    private String type;

    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER)
    private List<Employee> employees = new ArrayList<>();

    public Organization() {
    }

    public Organization(String name, String type, Date created) {
        super(created);
        this.name = name;
        this.type = type;
    }
    
    public Organization(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Organization{" + "name=" + name + ", type=" + type + '}';
    }
}
