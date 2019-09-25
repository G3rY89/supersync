package com.ks.supersync.model.ugyvitel.product;

import javax.xml.bind.annotation.*;

public class TranslatedName {
    
    @XmlAttribute(name = "LanguageId")
    public String languageId;
    @XmlValue
    public String name;

    public TranslatedName(String languageId, String name){
        this.languageId = languageId;
        this.name = name;
    }

    public TranslatedName(){

    }
}