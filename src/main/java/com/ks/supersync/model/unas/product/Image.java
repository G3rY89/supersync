package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

public class Image {

    @XmlElement(name = "Id")
    private Integer id;
    @XmlElement(name = "Type")
    private String type;
    @XmlElement(name = "Url")
    private Url url;
    @XmlElement(name = "Import")
    private Import importPic;
}