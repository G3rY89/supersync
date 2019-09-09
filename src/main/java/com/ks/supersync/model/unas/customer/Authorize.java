package com.ks.supersync.model.unas.customer;

import javax.xml.bind.annotation.XmlElement;

public class Authorize {

    @XmlElement(name = "Customer")
    public String customer;
    @XmlElement(name = "Admin")
    public String admin;
}