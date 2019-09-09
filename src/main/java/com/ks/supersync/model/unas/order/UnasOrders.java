package com.ks.supersync.model.unas.order;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Orders")
public class UnasOrders {

    @XmlElement(name = "Order")
    public List<UnasOrder> orders;

}