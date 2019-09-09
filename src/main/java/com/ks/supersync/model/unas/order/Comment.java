package com.ks.supersync.model.unas.order;

import javax.xml.bind.annotation.XmlElement;

public class Comment {

    @XmlElement(name = "Type")
    public String type;
    @XmlElement(name = "Text")
    public String text;
}