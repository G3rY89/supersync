package com.ks.supersync.model.ugyvitel.order.detail;

import java.util.List;

import javax.xml.bind.annotation.*;

public class Details {

    @XmlElement(name = "Detail")
    public List<Detail> detail;
}