package com.ks.supersync.model.unas.customer;

import javax.xml.bind.annotation.XmlElement;

public class Dates {

    @XmlElement(name = "Registration")
    public String registration;
    @XmlElement(name = "Modification")
    public String modification;
    @XmlElement(name = "Login")
    public String login;
}