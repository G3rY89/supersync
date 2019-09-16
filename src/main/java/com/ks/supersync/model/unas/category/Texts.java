package com.ks.supersync.model.unas.category;

import javax.xml.bind.annotation.XmlElement;

public class Texts {

    @XmlElement(name = "Top")
    public String top;
    @XmlElement(name = "Bottom")
    public String bottom;
    @XmlElement(name = "Menu")
    public String menu;
}