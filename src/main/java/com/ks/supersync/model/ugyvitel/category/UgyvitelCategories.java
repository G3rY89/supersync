package com.ks.supersync.model.ugyvitel.category;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Categories")
public class UgyvitelCategories {


    @XmlElement(name = "Category")
    public List<UgyvitelCategory> categories;
}