/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Hasanov (Asus)
 */
@MappedSuperclass
public abstract class AbstractDomain implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;
    
    @Column(name = "create_date", nullable = false, columnDefinition = "timestamp with time zone")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created = new Date();
    
    @Version
    private int version;
    
    public AbstractDomain() {
        
    }
    
    public AbstractDomain(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" + "id=" + id + ", created=" + created + ", version=" + version + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
