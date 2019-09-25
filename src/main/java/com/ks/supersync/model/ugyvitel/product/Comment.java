package com.ks.supersync.model.ugyvitel.product;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Comment{

    @XmlElement(name = "TranslatedComment")
    public List<TranslatedComment> tComment;

    public Comment(){
        this.tComment = new ArrayList<>();
    }

}