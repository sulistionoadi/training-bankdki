/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.project.trainingmodel.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author adi
 */

@Entity
@Table(name="transaction_detail")
public class TransactionDetail {
    
    @Id //Primary Key
    @GeneratedValue(generator = "system-uuid") //bikin random uuid string
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
    @ManyToOne
    @JoinColumn(name="id_header", nullable = false)
    private TransactionHeader header;
    
    @Column(name="description", nullable = false)
    private String description;
    
    @Column(name="amount", nullable = false)
    private BigDecimal amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TransactionHeader getHeader() {
        return header;
    }

    public void setHeader(TransactionHeader header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionDetail{" + "description=" + description + ", amount=" + amount + '}';
    }
    
}
