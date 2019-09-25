package com.ks.supersync.model.ugyvitel.product;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class QuantityUnit {

    @XmlElement(name = "TranslatedQuantityUnit")
    public List<TranslatedQuantityUnit> tQUnit;

    public QuantityUnit(){
        this.tQUnit = new ArrayList<>();
    }
}