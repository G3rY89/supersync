package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Export")
public class Format {

    @XmlAttribute(name = "Format")
    public String format;
}