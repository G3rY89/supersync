package com.ks.supersync.model.ugyvitel.customer;

import javax.xml.bind.annotation.XmlAttribute;

public class Category {

    @XmlAttribute(name = "CategoryId")
    public String categoryId;
    @XmlAttribute(name = "CategoryValue")
    public String categoryValue;
    @XmlAttribute(name = "IsBaseCategory")
    public String isBaseCategory;
}