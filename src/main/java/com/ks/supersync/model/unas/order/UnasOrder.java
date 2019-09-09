package com.ks.supersync.model.unas.order;

import java.util.List;

import javax.xml.bind.annotation.*;

import com.ks.supersync.model.unas.customer.Param;
import com.ks.supersync.model.unas.order.customer.Customer;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Order")
public class UnasOrder{

    @XmlElement(name = "Action")
    public String action;
    @XmlElement(name = "Key")
    public String key;
    @XmlElement(name = "Date")
    public String date;
    @XmlElement(name = "DateMod")
    public String dateMod;
    @XmlElement(name = "Customer")
    public Customer customer;
    @XmlElement(name = "Currency")
    public String currency;
    @XmlElement(name = "Type")
    public String type;
    @XmlElement(name = "Status")
    public String status;
    @XmlElement(name = "StatusDetails")
    public String statusDetails;
    @XmlElement(name = "StatusEmail")
    public String statusEmail;
    @XmlElement(name = "Payment")
    public Payment payment;
    @XmlElement(name = "Shipping")
    public Shipping shipping;
    @XmlElement(name = "Invoice")
    public Invoice invoice;
    @XmlElement(name = "Params")
    public List<Param> params;
    @XmlElement(name = "Referer")
    public String referer;
    @XmlElement(name = "Coupon")
    public String coupon;
    @XmlElement(name = "Info")
    public Info info;
    @XmlElement(name = "Comments")
    public List<Comment> comments;
    @XmlElement(name = "Items")
    public Items Items;

}