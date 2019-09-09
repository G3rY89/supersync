package com.ks.supersync.model.unas.customer;

import javax.xml.bind.annotation.XmlElement;

public class Discount {

    @XmlElement(name = "Total")
    public Integer total;
    @XmlElement(name = "Direct")
    public Integer direct;
}