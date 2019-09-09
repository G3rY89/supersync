package com.ks.supersync.model.unas.customer;

import java.util.List;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Customers")
public class UnasCustomers {

    @XmlElement(name = "Customer")
    public List<UnasCustomer> customer;

}