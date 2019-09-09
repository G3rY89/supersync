package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

public class Param {

    @XmlElement(name = "Id")
    private Integer id;
    @XmlElement(name = "Type")
    private String type;
    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "Group")
    private String group;
    @XmlElement(name = "Value")
    private String value;
    @XmlElement(name = "Before")
    private String before;
    @XmlElement(name = "After")
    private String after;

}