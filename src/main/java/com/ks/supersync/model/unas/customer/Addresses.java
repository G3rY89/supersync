package com.ks.supersync.model.unas.customer;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.ks.supersync.model.unas.customer.addresses.Invoice;
import com.ks.supersync.model.unas.customer.addresses.Other;
import com.ks.supersync.model.unas.customer.addresses.Shipping;

public class Addresses {

    @XmlElement(name = "Invoice")
    public Invoice invoice;
    @XmlElement(name = "Shipping")
    public Shipping shipping;
    @XmlElement(name = "Others")
    public List<Other> others;

    public Addresses(){
        this.invoice = new Invoice();
        this.shipping = new Shipping();
        this.others = new ArrayList<>();
    }
}
