package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

public class PublicInterval {

    @XmlElement(name = "Start")
    public String start;
    @XmlElement(name = "End")
    public String end;
}