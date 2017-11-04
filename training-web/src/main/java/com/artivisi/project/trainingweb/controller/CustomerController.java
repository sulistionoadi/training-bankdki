/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.project.trainingweb.controller;

import com.artivisi.project.trainingmodel.dao.CustomerDao;
import com.artivisi.project.trainingmodel.entity.Customer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/customer/list", method = RequestMethod.GET)
    public void showListCustomer(ModelMap mm) {
        log.info("Tampilkan data list customer");

        Iterable<Customer> listCustomer = customerDao.findAll();
        mm.addAttribute("listCustomers", listCustomer);
    }

    @GetMapping("/customer/download")
    public void downloadCustomer(HttpServletResponse response) throws JRException, IOException {
        Iterable<Customer> listCustomer = customerDao.findAll();
        InputStream jasperStream = getClass().getResourceAsStream("/templateJasper/laporan_customer.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), new JRBeanCollectionDataSource((Collection<?>) listCustomer));

        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }

    @RequestMapping(value = "/customer/form", method = RequestMethod.GET)
    public void showFormCustomer(ModelMap mm, @RequestParam(required = false) String id) {
        Customer cust = null;
        if (StringUtils.hasText(id)) {
            cust = customerDao.findOne(id);
        }

        if (cust == null) {
            cust = new Customer();
        }

        mm.addAttribute("customer", cust);
    }

    @RequestMapping(value = "/customer/form", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerDao.save(customer);
        return "redirect:list";
    }

    @RequestMapping(value = "/customer/delete", method = RequestMethod.POST)
    public String saveCustomer(@RequestParam String id) {
        Customer cust = customerDao.findOne(id);
        if (cust != null) {
            customerDao.delete(cust);
        }

        return "redirect:list";
    }

}
