package com.ks.supersync.model.ugyvitel.product;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Name {

    @XmlElement(name = "TranslatedName")
    public List<TranslatedName> tName;

    public Name(){
        this.tName = new ArrayList<>();
    }

}