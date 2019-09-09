package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

public class Import {

    @XmlElement(name = "Url")
    private String url;
    @XmlElement(name = "Encoded")
    private String encoded;

}