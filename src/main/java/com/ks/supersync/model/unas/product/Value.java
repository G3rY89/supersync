package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

public class Value {

    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "ExtraPrice")
    private Integer extraPrice;

}