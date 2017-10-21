/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.project.trainingmodel.service;

import com.artivisi.project.trainingmodel.dao.BalanceCustomerDao;
import com.artivisi.project.trainingmodel.dao.TransactionHeaderDao;
import com.artivisi.project.trainingmodel.entity.BalanceCustomer;
import com.artivisi.project.trainingmodel.entity.TransactionHeader;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author adi
 */

@Service
public class TransactionService {
    
    @Autowired private TransactionHeaderDao transactionHeaderDao;
    @Autowired private BalanceCustomerDao balanceCustomerDao;
    @Autowired private EntityManager entityManager;
    
    @Transactional(rollbackOn = Exception.class)
    public void saveTrxAndUpdateBalance(TransactionHeader header) throws Exception{
        
        BalanceCustomer bal = null;
        
        try {
            String sql = "SELECT bal FROM BalanceCustomer bal "
                       + "WHERE bal.customer.id = :idCustomer ";
            
            bal = (BalanceCustomer) entityManager.createQuery(sql)
                    .setParameter("idCustomer", header.getCustomer().getId())
                    .setLockMode(LockModeType.PESSIMISTIC_WRITE) //SELECT ... FOR UPDATE
                    .getSingleResult();
            
        } catch(NoResultException ex){
            bal = new BalanceCustomer();
            bal.setCustomer(header.getCustomer());
            bal.setBalance(BigDecimal.ZERO);
        }
        
        transactionHeaderDao.save(header);
        
        if(bal.getBalance().compareTo(header.getTransactionAmount()) < 1) {
            throw new Exception("Insufficient Balance. your balance is : " + bal.getBalance());
        }
        
        bal.setBalance(bal.getBalance().subtract(header.getTransactionAmount()));
        balanceCustomerDao.save(bal);
    }
    
}
