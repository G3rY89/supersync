package com.ks.supersync.model.unas.category;

import javax.xml.bind.annotation.XmlElement;

public class Meta {

    @XmlElement(name = "Keywords")
    public String keywords;
    @XmlElement(name = "Description")
    public String description;
    @XmlElement(name = "Title")
    public String title;
    @XmlElement(name = "Robots")
    public String robots;
}