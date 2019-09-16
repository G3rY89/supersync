package com.ks.supersync.model.unas.category;

import javax.xml.bind.annotation.XmlElement;

public class Display {

    @XmlElement(name = "Page")
    public String page;
    @XmlElement(name = "Menu")
    public String menu;
}