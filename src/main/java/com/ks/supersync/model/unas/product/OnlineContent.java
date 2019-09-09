package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

public class OnlineContent {

    @XmlElement(name = "Url")
    private String url;
    @XmlElement(name = "Limit")
    private String limit;
    @XmlElement(name = "Filename")
    private String fileName;
}