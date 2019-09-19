package com.ks.supersync.model.ugyvitel.customer;

import javax.xml.bind.annotation.*;

public class Category {

    @XmlElement(name = "CategoryId")
    public String categoryId;
    @XmlElement(name = "CategoryValue")
    public String categoryValue;
    @XmlElement(name = "IsBaseCategory")
    public String isBaseCategory;
}