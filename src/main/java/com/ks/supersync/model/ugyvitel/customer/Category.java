package com.ks.supersync.model.ugyvitel.customer;

import javax.xml.bind.annotation.*;

public class Category {

    @XmlElement(name = "CategoryId")
    public String categoryId;
    @XmlElement(name = "CategoryValue")
    public String categoryValue;
    @XmlElement(name = "CategoryType")
    public String categoryType;

    public Category(String categroryId, String CategoryValue){
        this.categoryId = categroryId;
        this.categoryValue = CategoryValue;
    }

    public Category(){
        
    }
}