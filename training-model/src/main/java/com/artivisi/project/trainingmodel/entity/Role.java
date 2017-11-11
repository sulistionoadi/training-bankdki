/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.project.trainingmodel.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author adi
 */

@Entity
@Table(name="sec_role")
public class Role implements Serializable {
    
    @Id //Primary Key
    @GeneratedValue(generator = "system-uuid") //bikin random uuid string
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
    @Column(name="role_name", unique = true, nullable = false)
    private String name;
    
    @Column(name="role_desc", nullable = false)
    private String description;
    
    @ManyToMany
    @JoinTable(name="sec_role_permission", 
            joinColumns = @JoinColumn(name="id_role"),
            inverseJoinColumns = @JoinColumn(name="id_permission"))
    private Set<Permission> listPermission = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Permission> getListPermission() {
        return listPermission;
    }

    public void setListPermission(Set<Permission> listPermission) {
        this.listPermission = listPermission;
    }
    
}
