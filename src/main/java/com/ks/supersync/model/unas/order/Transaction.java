package com.ks.supersync.model.unas.order;

import javax.xml.bind.annotation.XmlElement;

public class Transaction {
    
    @XmlElement(name = "Id")
    public String id;
    @XmlElement(name = "AuthCode")
    public String authCode;
    @XmlElement(name = "Status")
    public String status;
    @XmlElement(name = "date")
    public String date;
    @XmlElement(name = "Amount")
    public String amount;
}