/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Hasanov (Asus)
 */
@Entity
@Table(name = "employees")
public class Employee extends AbstractDomain {
    
    @Column
    private String fullname;
    
    @Column
    private String position;
    
    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    public Employee() {
    }

    public Employee(String fullname, String position, Organization organization, Date created) {
        super(created);
        this.fullname = fullname;
        this.position = position;
        this.organization = organization;
    }
    
    public Employee(String fullname, String position, Organization organization) {
        this.fullname = fullname;
        this.position = position;
        this.organization = organization;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "Employee{" + "fullname=" + fullname + ", position=" + position + ", organization=" + organization + '}';
    }
}
