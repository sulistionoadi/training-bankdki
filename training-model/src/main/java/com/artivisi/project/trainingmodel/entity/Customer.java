/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.project.trainingmodel.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author adi
 */

@Entity
@Table(name = "mst_customer")
public class Customer {

    @Id //Primary Key
    @GeneratedValue(generator = "system-uuid") //bikin random uuid string
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
   
    @Column(name="customer_id", length = 20, nullable = false)
    private String customerId;
    
    @Column(name="firstname", length = 25, nullable = false)
    private String firstname;

    @Column(name="lastname", length = 25)
    private String lastname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name="birth_date", nullable = false)
    private Date birthDate;

    @Column(name="address", length = 255, nullable = false)
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", customerId=" + customerId + ", firstname=" + firstname + ", lastname=" + lastname + ", birthDate=" + birthDate + ", address=" + address + '}';
    }
    
}
