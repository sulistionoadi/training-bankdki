/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.project.trainingweb.controller;

import com.artivisi.project.trainingmodel.dao.CustomerDao;
import com.artivisi.project.trainingmodel.dao.TransactionHeaderDao;
import com.artivisi.project.trainingmodel.entity.TransactionDetail;
import com.artivisi.project.trainingmodel.entity.TransactionHeader;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ivans
 */
@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionHeaderDao transactionHeaderDao;

    @Autowired
    private CustomerDao customerDao;

    @GetMapping("/list")
    public String getList(ModelMap mm) {
        Iterable<TransactionHeader> result = transactionHeaderDao.findAll();
        mm.addAttribute("listTransaction", result);
        return "transaction/list";
    }

    @GetMapping("/form")
    public String getForm(ModelMap mm) {
        TransactionHeader header = new TransactionHeader();
        mm.addAttribute("header", header);
        mm.addAttribute("listCustomer", customerDao.findAll());
        return "transaction/form";
    }

    @PostMapping("/form")
    public String saveHeader(TransactionHeader transactionHeader) {
        for(TransactionDetail detail : transactionHeader.getDetails()){
            detail.setHeader(transactionHeader);
        }
        
        transactionHeaderDao.save(transactionHeader);

        return "redirect:/transaction/list";
    }

    @PostMapping(value = "/form", params = {"add"})
    public String addDetail(TransactionHeader transactionHeader, ModelMap mm) {
        TransactionDetail detail = new TransactionDetail();
        transactionHeader.getDetails().add(detail);
        
        mm.addAttribute("header", transactionHeader);
        mm.addAttribute("listCustomer", customerDao.findAll());
        return "transaction/form";
    }

    @PostMapping(value = "/form", params = {"remove"})
    public String removeDetail(TransactionHeader transactionHeader, HttpServletRequest request, ModelMap mm) {
        
        int index = Integer.parseInt(request.getParameter("remove"));
        
        transactionHeader.getDetails().remove(index);
        
        mm.addAttribute("header", transactionHeader);
        mm.addAttribute("listCustomer", customerDao.findAll());
        return "transaction/form";
    }
}
