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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PreAuthorize("hasRole('ROLE_MENU_LIST_TRANSAKSI')")
    @GetMapping("/list")
    public String getList(ModelMap mm) {
        Iterable<TransactionHeader> result = transactionHeaderDao.findAll();
        mm.addAttribute("listTransaction", result);
        return "transaction/list";
    }

    @PreAuthorize("hasRole('ROLE_MENU_FORM_TRANSAKSI')")
    @GetMapping("/form")
    public String getForm(@RequestParam(required = false) String id, ModelMap mm) {
        TransactionHeader header = new TransactionHeader();
        if(id != null && id != ""){
            header = transactionHeaderDao.findOne(id);
        }
        
        mm.addAttribute("header", header);
        mm.addAttribute("listCustomer", customerDao.findAll());
        return "transaction/form";
    }
    
    @PreAuthorize("hasRole('ROLE_ACT_DEL_TRANSAKSI')")
    @GetMapping("/delete")
    public String deleteHeader(@RequestParam() String id){
        transactionHeaderDao.delete(id);
        return "redirect:/transaction/list";
    }

    @PreAuthorize("hasRole('ROLE_MENU_FORM_TRANSAKSI')")
    @PostMapping("/form")
    public String saveHeader(TransactionHeader transactionHeader) {
        for(TransactionDetail detail : transactionHeader.getDetails()){
            detail.setHeader(transactionHeader);
        }
        
        transactionHeaderDao.save(transactionHeader);

        return "redirect:/transaction/list";
    }

    @PreAuthorize("hasRole('ROLE_MENU_FORM_TRANSAKSI')")
    @PostMapping(value = "/form", params = {"add"})
    public String addDetail(TransactionHeader transactionHeader, ModelMap mm) {
        TransactionDetail detail = new TransactionDetail();
        transactionHeader.getDetails().add(detail);
        
        mm.addAttribute("header", transactionHeader);
        mm.addAttribute("listCustomer", customerDao.findAll());
        return "transaction/form";
    }

    @PreAuthorize("hasRole('ROLE_MENU_FORM_TRANSAKSI')")
    @PostMapping(value = "/form", params = {"remove"})
    public String removeDetail(TransactionHeader transactionHeader, HttpServletRequest request, ModelMap mm) {
        
        int index = Integer.parseInt(request.getParameter("remove"));
        
        transactionHeader.getDetails().remove(index);
        
        mm.addAttribute("header", transactionHeader);
        mm.addAttribute("listCustomer", customerDao.findAll());
        return "transaction/form";
    }
}
