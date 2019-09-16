package com.ks.supersync.model.unas.category;

import javax.xml.bind.annotation.XmlElement;

public class Image {

    @XmlElement(name = "Url")
    public String url;
    @XmlElement(name = "OG")
    public String og;
}