package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;


public class Category {

    @XmlElement(name = "Type")
    public String type;
    @XmlElement(name = "Id")
    public String id;
    @XmlElement(name = "Name")
    public String name;
}