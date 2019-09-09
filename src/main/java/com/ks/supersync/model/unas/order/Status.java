package com.ks.supersync.model.unas.order;

import javax.xml.bind.annotation.XmlElement;

public class Status {

    @XmlElement(name = "Id")
    public String id;
    @XmlElement(name = "Name")
    public String name;
    @XmlElement(name = "Value")
    public String value;
    @XmlElement(name = "Public")
    public String isPublic;
}