package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

public class History {

    @XmlElement(name = "Action")
    private String action;
    @XmlElement(name = "Time")
    private String time;
    @XmlElement(name = "Sku")
    private String sku;
    @XmlElement(name = "SkuOld")
    private String skuOld;

}