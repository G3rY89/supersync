package com.ks.supersync.model.ugyvitel.product;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

public class ProductName {

    @XmlElement(name = "TranslatedName")
    @XmlAttribute(name = "LanguageId")
    public List<TranslatedName> pName;

    public ProductName(){
        this.pName = new ArrayList<>();
    }
}