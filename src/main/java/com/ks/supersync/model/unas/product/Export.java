package com.ks.supersync.model.unas.product;

import java.util.List;

import javax.xml.bind.annotation.*;

public class Export {

    @XmlElement(name = "Status")
    private Integer status;
    @XmlElement(name = "Forbidden")
    private List<Format> forbidden;

}