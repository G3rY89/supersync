package com.ks.supersync.model.ugyvitel.product;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Stocks {

    @XmlElement(name = "Stock")
    public List<Stock> stock;

    public Stocks(){
        this.stock = new ArrayList<>();
    }
}