package com.ks.supersync.model.unas.order.customer;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.ks.supersync.model.unas.customer.Param;

public class Customer {

    @XmlElement(name = "Id")
    public String id;
    @XmlElement(name = "Email")
    public String email;
    @XmlElement(name = "Username")
    public String userName;
    @XmlElement(name = "Contact")
    public Contact contact;
    @XmlElement(name = "Addresses")
    public Addresses addresses;
    @XmlElement(name = "Group")
    public Group group;
    @XmlElement(namespace = "Params")
    public List<Param> params;
    @XmlElement(name = "Comment")
    public String comment;
    
}