package com.ks.supersync.model.unas.product.stock;

import javax.xml.bind.annotation.*;

public class Status {

    @XmlElement(name = "Active")
    public Integer active;
    @XmlElement(name = "Empty")
    public Integer empty;
    @XmlElement(name = "Variant")
    public Integer variant;
}