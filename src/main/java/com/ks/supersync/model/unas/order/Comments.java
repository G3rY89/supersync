package com.ks.supersync.model.unas.order;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Comments {

    @XmlElement(name = "Comment")
    public List<Comment> comments;

    public Comments(){
        this.comments = new ArrayList<>();
    }

}