package com.ks.supersync.model.unas.category;

import javax.xml.bind.annotation.XmlElement;

public class Products {

    @XmlElement(name = "All")
    public String all;
    @XmlElement(name = "New")
    public String newProducts;
}