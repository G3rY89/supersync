package com.ks.supersync.model.ugyvitel.product;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class TranslatedComment {

    @XmlAttribute(name = "LanguageId")
    public String languageId;
    @XmlValue
    public String comment;

    public TranslatedComment(String languageId, String comment){
        this.languageId = languageId;
        this.comment = comment;
    }
}