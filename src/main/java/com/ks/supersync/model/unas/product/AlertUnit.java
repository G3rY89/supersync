package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

public class AlertUnit {

    @XmlElement(name = "Qty")
    public Integer qty;
    @XmlElement(name = "Unit")
    public Integer unit;
}