package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

public class AdditionalProduct {

    @XmlElement(name = "Id")
    private Integer id;
    @XmlElement(name = "Sku")
    private String sku;
    @XmlElement(name = "Name")
    private String name;
}