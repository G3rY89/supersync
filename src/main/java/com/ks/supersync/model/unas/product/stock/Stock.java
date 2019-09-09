package com.ks.supersync.model.unas.product.stock;

import java.util.List;

import javax.xml.bind.annotation.*;

public class Stock {

    @XmlElement(name = "Status")
    public Status status;
    @XmlElement(name = "Variants")
    public List<String> stock;
}