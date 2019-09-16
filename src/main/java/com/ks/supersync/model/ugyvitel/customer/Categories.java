package com.ks.supersync.model.ugyvitel.customer;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Categories {

    @XmlElement(name = "Category")
    public List<Category> category;
}