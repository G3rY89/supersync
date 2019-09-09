package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

public class Type {

    @XmlElement(name = "Type")
    private String type;
    @XmlElement(name = "Parent")
    private String parent;
    @XmlElement(name = "Display")
    private Integer display;
    @XmlElement(name = "Order")
    private Integer order;
}