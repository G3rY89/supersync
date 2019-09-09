package com.ks.supersync.model.unas.customer;

import javax.xml.bind.annotation.XmlElement;

public class Restriction{

    @XmlElement(name = "Type")
    public String type;
    @XmlElement(name = "Id")
    public Integer id;
    @XmlElement(name = "Name")
    public String name;
}