package com.ks.supersync.model.unas.product;

import java.util.List;

import javax.xml.bind.annotation.*;

public class Variant {

    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "Values")
    private List<Value> values;

}