package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

public class Limit {

    @XmlElement(name = "Lower")
    private Integer lower;
    @XmlElement(name = "Upper")
    private Integer upper;
}