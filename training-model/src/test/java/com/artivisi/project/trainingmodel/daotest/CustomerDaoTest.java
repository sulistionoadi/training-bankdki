/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.project.trainingmodel.daotest;

import com.artivisi.project.trainingmodel.dao.CustomerDao;
import com.artivisi.project.trainingmodel.entity.Customer;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author adi
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerDaoTest {
    
    @Autowired
    private CustomerDao customerDao;
    
//    @Test
    public void testSaveCustomer(){
        Customer cust = new Customer();
        cust.setCustomerId("35781313000944");
        cust.setFirstname("RAHADI");
        cust.setLastname("RACHMAT");
        cust.setBirthDate(new Date());
        cust.setAddress("BANDUNG");
        
        customerDao.save(cust);
    }
    
    //@Test
    public void testUpdateCustomer(){
        Customer cust = customerDao.findOne("6c6e9c6d-bfda-453e-b7d1-3fdb651a9c52");
        if(cust != null){
            cust.setAddress("JAKARTA TIMUR");
            customerDao.save(cust);
        }
    }
    
    //@Test
    public void testDeleteCustomer(){
        Customer cust = customerDao.findOne("6c6e9c6d-bfda-453e-b7d1-3fdb651a9c52");
        if(cust != null){
            customerDao.delete(cust);
        }
    }
    
    @Test
    public void testCustomQuery(){
        List<Customer> result = customerDao.findByFirstnameContaining("RAH");
        //List<Customer> result = customerDao.getByFirstnameOrLastname("%RAH%", "%U%");
        
        System.out.println("\n\n");
        for (Customer customer : result) {
            System.out.println(customer.toString());
        }
        System.out.println("\n\n");
    }
}
