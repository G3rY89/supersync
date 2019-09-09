package com.ks.supersync.model.unas.product;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Categories {

    @XmlElement(name = "Category")
    public List<Category> category;
}