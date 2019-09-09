package com.ks.supersync.model.ugyvitel.order;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Orders")
public class UgyvitelOrders{

    @XmlElement(name = "Order")
    public List<UgyvitelOrder> order;
}