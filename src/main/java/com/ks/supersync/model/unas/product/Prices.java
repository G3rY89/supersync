package com.ks.supersync.model.unas.product;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Prices{

    @XmlElement(name = "Vat")
    public String vat;
    @XmlElement(name = "Price")
    public List<Price> prices;
}