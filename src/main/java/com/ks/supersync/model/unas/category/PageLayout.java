package com.ks.supersync.model.unas.category;

import javax.xml.bind.annotation.XmlElement;

public class PageLayout {

    @XmlElement(name = "ProductList")
    public String productList;
    @XmlElement(name = "CategoryList")
    public String categoryList;
}