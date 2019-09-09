package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

public class Data {

    @XmlElement(name = "Id")
    private Integer id;
    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "Value")
    private String value;

}