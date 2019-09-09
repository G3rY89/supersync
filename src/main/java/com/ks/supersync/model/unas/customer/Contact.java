package com.ks.supersync.model.unas.customer;

import javax.xml.bind.annotation.XmlElement;

public class Contact {

    @XmlElement(name = "Name")
    public String name;
    @XmlElement(name = "Phone")
    public String phone;
    @XmlElement(name = "Mobile")
    public String mobile;
    @XmlElement(name = "Lang")
    public String lang;
}