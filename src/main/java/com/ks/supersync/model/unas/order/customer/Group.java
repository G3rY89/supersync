package com.ks.supersync.model.unas.order.customer;

import javax.xml.bind.annotation.XmlElement;

public class Group {

    @XmlElement(name = "Id")
    public Integer id;
    @XmlElement(name = "Name")
    public String name;
}