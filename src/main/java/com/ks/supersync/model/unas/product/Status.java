package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Status {

    @XmlElement(name = "Type")
    private String type;
    @XmlElement(name = "Value")
    private String value;

}