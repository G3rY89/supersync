package com.ks.supersync.model.ugyvitel.product;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class PriceRules {

    @XmlElement(name = "PriceRule")
    public List<PriceRule> priceRule;

    public PriceRules(){
        this.priceRule = new ArrayList<>();
    }
}