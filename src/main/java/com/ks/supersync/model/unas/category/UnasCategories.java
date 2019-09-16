package com.ks.supersync.model.unas.category;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Categories")
public class UnasCategories {

    @XmlElement(name = "Category")
    public List<UnasCategory> customer;

}