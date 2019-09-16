package com.ks.supersync.model.unas.category;

import javax.xml.bind.annotation.XmlElement;

public class Parent {

    @XmlElement(name = "Id")
    public String id;
    @XmlElement(name = "Tree")
    public String tree;
}