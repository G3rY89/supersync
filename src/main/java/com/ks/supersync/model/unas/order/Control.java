package com.ks.supersync.model.unas.order;

import javax.xml.bind.annotation.XmlElement;

public class Control {

    @XmlElement(name = "Quantity")
    public String quantity;
    @XmlElement(name = "User")
    public String user;
}