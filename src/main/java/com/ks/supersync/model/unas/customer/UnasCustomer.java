package com.ks.supersync.model.unas.customer;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class UnasCustomer {

    @XmlElement(name = "Action")
    public String action;
    @XmlElement(name = "Id")
    public String id;
    @XmlElement(name = "Email")
    public String email;
    @XmlElement(name = "Username")
    public String userName;
    @XmlElement(name = "Password")
    public String password;
    @XmlElement(name = "PasswordCrypted")
    public String passwordCrypted;
    @XmlElement(name = "Contact")
    public Contact contact;
    @XmlElement(name = "Addresses")
    public Addresses addresses;
    @XmlElement(name = "Params")
    public List<Param> params;
    @XmlElement(name = "Dates")
    public Dates dates;
    @XmlElement(name = "Group")
    public Group group;
    @XmlElement(name = "Authorize")
    public Authorize authorize;
    @XmlElement(name = "Discount")
    public Discount discount;
    @XmlElement(name = "PointsAccount")
    public PointsAccount pointsAccount;
    @XmlElement(name = "Newsletter")
    public Newsletter newletter;
    @XmlElement(name = "Comment")
    public String comment;
    @XmlElement(name = "Restrictions")
    public List<Restriction> restrictions;
    @XmlElement(name = "Others")
    public Others others;
    @XmlElement(name = "Status")
    public String status;
    @XmlElement(name = "Error")
    public String error;

    public UnasCustomer(){
        this.contact = new Contact();
        this.addresses = new Addresses();
        this.group = new Group();
    }
}