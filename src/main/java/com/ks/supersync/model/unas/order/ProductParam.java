package com.ks.supersync.model.unas.order;

import javax.xml.bind.annotation.XmlElement;

public class ProductParam {

    @XmlElement(name = "Id")
    public String id;
    @XmlElement(name = "Name")
    public String name;
    @XmlElement(name = "Value")
    public String value;
}