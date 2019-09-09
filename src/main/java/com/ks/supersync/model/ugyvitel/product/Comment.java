package com.ks.supersync.model.ugyvitel.product;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Comment {

    @XmlElement(name = "TranslatedComment")
    public List<String> comment;

    public Comment() {
        this.comment = new ArrayList<>();   
    }
}