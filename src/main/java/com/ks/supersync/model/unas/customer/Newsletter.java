package com.ks.supersync.model.unas.customer;

import javax.xml.bind.annotation.XmlElement;

public class Newsletter {

    @XmlElement(name = "Subscribed")
    public String subscribed;
    @XmlElement(name = "Authorized")
    public String authorized;
}