/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.project.trainingweb.controller;

import com.artivisi.project.trainingmodel.dao.CustomerDao;
import com.artivisi.project.trainingmodel.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author adi
 */

@Controller
public class CustomerController {
    
    @Autowired 
    private CustomerDao customerDao;
    
    @RequestMapping(value="/customer/list", method=RequestMethod.GET)
    public void showListCustomer(ModelMap mm){
        Iterable<Customer> listCustomer = customerDao.findAll();
        mm.addAttribute("listCustomers", listCustomer);
    }
    
    @RequestMapping(value="/customer/form", method=RequestMethod.GET)
    public void showFormCustomer(ModelMap mm, @RequestParam(required = false) String id){
        Customer cust = null;
        if(StringUtils.hasText(id)){
            cust = customerDao.findOne(id);
        }
        
        if(cust == null){
            cust = new Customer();
        }
        
        mm.addAttribute("customer", cust);
    }
    
    @RequestMapping(value="/customer/form", method=RequestMethod.POST)
    public String saveCustomer(@ModelAttribute Customer customer){
        customerDao.save(customer);
        return "redirect:list";
    }
    
}
