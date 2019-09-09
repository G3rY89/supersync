package com.ks.supersync.model.ugyvitel.product;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Products")
public class UgyvitelProducts{

    @XmlElement(name = "Product")
    public List<UgyvitelProduct> product;
}