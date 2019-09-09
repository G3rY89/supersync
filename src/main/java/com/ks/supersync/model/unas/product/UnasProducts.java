package com.ks.supersync.model.unas.product;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Products")
public class UnasProducts{

    @XmlElement(name = "Product")
    public List<UnasProduct> products;

}