/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.project.trainingmodel.daotest;

import com.artivisi.project.trainingmodel.dao.CustomerDao;
import com.artivisi.project.trainingmodel.dao.TransactionHeaderDao;
import com.artivisi.project.trainingmodel.entity.Customer;
import com.artivisi.project.trainingmodel.entity.TransactionDetail;
import com.artivisi.project.trainingmodel.entity.TransactionHeader;
import com.artivisi.project.trainingmodel.service.TransactionService;
import java.math.BigDecimal;
import java.util.Date;
import javax.transaction.Transactional;
import org.junit.Assert;
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
public class TransactionTest {
    
    @Autowired private CustomerDao customerDao;
    @Autowired private TransactionHeaderDao transactionDao;
    @Autowired private TransactionService transactionService;
    
//    @Test
    public void saveTransaction(){
        Customer customer = customerDao.findOne("11c6cc65-801a-456e-a8e2-8f57a8abf449");
        
        Assert.assertNotNull(customer);
        
        TransactionHeader header = new TransactionHeader();
        header.setCustomer(customer);
        header.setTransactionDate(new Date());
        
        TransactionDetail detail1 = new TransactionDetail();
        detail1.setHeader(header);
        detail1.setDescription("Transfer antar bank");
        detail1.setAmount(new BigDecimal(20000));
        header.getDetails().add(detail1);
        
        TransactionDetail detail2 = new TransactionDetail();
        detail2.setHeader(header);
        detail2.setDescription("biaya admin");
        detail2.setAmount(new BigDecimal(6500));
        header.getDetails().add(detail2);
        
        header.setTransactionAmount(detail1.getAmount().add(detail2.getAmount()));
        
        try {
            //transactionDao.save(header);
            transactionService.saveTrxAndUpdateBalance(header);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Test
    @Transactional
    public void testFetch(){
        Iterable<TransactionHeader> listHeader = transactionDao.findAll();

        for (TransactionHeader header : listHeader) {
            System.out.println("\n\n--> Transaction : " + header.toString());
            for(TransactionDetail det : header.getDetails()){
                System.out.println("--> Trx Detail : " + det.toString());
            }
            System.out.print("\n");
        }
    }
    
//    @Test
    public void deleteTransaction(){
        TransactionHeader header = transactionDao.findOne("4acd04ce-07d3-4319-9444-5b959197abcc");
        Assert.assertNotNull(header);
        
        transactionDao.delete(header);
    }
    
}
