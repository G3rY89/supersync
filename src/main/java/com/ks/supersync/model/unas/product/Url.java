package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

public class Url {

    @XmlElement(name = "Small")
    private String small;
    @XmlElement(name = "Medium")
    private String medium;
    @XmlElement(name = "Big")
    private String big;
}