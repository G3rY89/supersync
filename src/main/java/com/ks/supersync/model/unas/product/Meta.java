package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

public class Meta {

    @XmlElement(name = "Keywords")
    private String keywords;
    @XmlElement(name = "Description")
    private String description;
    @XmlElement(name = "Title")
    private String title;

}