package com.ks.supersync.model.unas.order;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class SeparatedTo {

    @XmlElement(name = "Key")
    public List<Key> key;
}