package com.ks.supersync.model.unas.product.stock;

import javax.xml.bind.annotation.XmlElement;

public class Stock {

    @XmlElement(name = "Status")
    public Status status;
    @XmlElement(name = "Qty")
    public Double qty;
    @XmlElement(name = "Price")
    public String price;

}