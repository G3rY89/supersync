package com.ks.supersync.model.unas.customer;

import javax.xml.bind.annotation.XmlElement;

public class Param{

    @XmlElement(name = "Id")
    public Integer id;
    @XmlElement(name = "Name")
    public String name;
    @XmlElement(name = "Value")
    public String value;
}