/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.project.trainingmodel.dao;

import com.artivisi.project.trainingmodel.entity.TransactionHeader;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author adi
 */
public interface TransactionHeaderDao extends CrudRepository<TransactionHeader, String> {
    
}
