package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

public class QtyDiscount {

    @XmlElement(name = "Limit")
    private Limit limit;
    @XmlElement(name = "Discount")
    private String discount;
}