package com.ks.supersync.model.ugyvitel.product;

import javax.xml.bind.annotation.XmlAttribute;

public class Category {

    @XmlAttribute(name = "CategoryValue")
    public String categoryValue;
    @XmlAttribute(name = "CategoryId")
    public String categoryId;
    @XmlAttribute(name = "CategoryType")
    public String categoryType;
}