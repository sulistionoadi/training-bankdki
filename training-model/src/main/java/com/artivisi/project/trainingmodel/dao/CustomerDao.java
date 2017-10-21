/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.project.trainingmodel.dao;

import com.artivisi.project.trainingmodel.entity.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author adi
 */
// CrudRepository <entityName, pkTypeData>
public interface CustomerDao extends CrudRepository<Customer, String>{
    
    public List<Customer> findByFirstnameContaining(String fistname);
    
    public List<Customer> findByFirstnameContainingOrLastnameContaining(String fistname, String lastname);
 
    @Query("SELECT cust FROM Customer cust "
            + "WHERE cust.firstname like :firstname "
            + "OR cust.lastname like :lastname")
    public List<Customer> getByFirstnameOrLastname(
                @Param("firstname") String firstname,
                @Param("lastname") String lastname);
}
