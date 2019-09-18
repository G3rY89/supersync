package com.ks.supersync.model.unas.customer;

import javax.xml.bind.annotation.XmlElement;

public class Group {

    @XmlElement(name = "Id")
    public String id;
    @XmlElement(name = "Name")
    public String name;
}