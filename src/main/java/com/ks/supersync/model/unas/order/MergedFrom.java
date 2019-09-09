package com.ks.supersync.model.unas.order;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class MergedFrom {
    
    @XmlElement(name = "Key")
    public List<Key> key;
}