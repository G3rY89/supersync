package com.ks.supersync.model.unas.order;

import javax.xml.bind.annotation.XmlElement;

public class Payment {
    
    @XmlElement(name = "Id")
    public String id;
    @XmlElement(name = "Name")
    public String name;
    @XmlElement(name = "Type")
    public String type;
    @XmlElement(name = "Status")
    public String status;
    @XmlElement(name = "Paid")
    public String paid;
    @XmlElement(name = "Pending")
    public String pending;
    @XmlElement(name = "Transactions")
    public Transactions transactions;
}