package com.ks.supersync.model.ugyvitel.product;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class QuantityUnit {

    @XmlElement(name = "TranslatedQuantityUnit")
    public List<String> TranslatedQuantityUnit;

    public QuantityUnit(){
        this.TranslatedQuantityUnit = new ArrayList<>();
    }
}