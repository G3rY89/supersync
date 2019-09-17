package com.ks.supersync.model.unas.product.stock;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

public class Stocks {

    @XmlElement(name = "Status")
    public Status status;
    @XmlElement(name = "Stock")
    public List<Stock> stock;
    
    public Stocks(){
        this.stock =  new ArrayList<>();
    }
    
}