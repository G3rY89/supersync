package com.ks.supersync.model.unas.order.customer;

import javax.xml.bind.annotation.XmlElement;

import com.ks.supersync.model.unas.customer.addresses.Invoice;
import com.ks.supersync.model.unas.customer.addresses.Shipping;

public class Addresses {

    @XmlElement(name = "Invoice")
    public Invoice invoice;
    @XmlElement(name = "Shipping")
    public Shipping shipping;

    public Addresses(){
        this.invoice = new Invoice();
        this.shipping = new Shipping();
    }
}