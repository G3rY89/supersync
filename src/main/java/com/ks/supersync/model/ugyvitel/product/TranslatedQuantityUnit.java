package com.ks.supersync.model.ugyvitel.product;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class TranslatedQuantityUnit {

    @XmlAttribute(name = "LanguageId")
    public String languageId;
    @XmlValue
    public String quantityUnit;

    public TranslatedQuantityUnit(String languageId, String quantityUnit){
        this.languageId = languageId;
        this.quantityUnit = quantityUnit;
    }
}