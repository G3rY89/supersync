package com.ks.supersync.model.unas.order;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Items {

    @XmlElement(name = "Item")
    public List<Item> item;
}