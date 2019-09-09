package com.ks.supersync.model.unas.order;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Transactions {

    @XmlElement(name = "Transaction")
    public List<Transaction> Transactions;
    @XmlElement(name = "ForeignID")
    public String foreignID;
    
}