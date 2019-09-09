package com.ks.supersync.model.ugyvitel.customer;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Customers")
public class UgyvitelCustomers{

    @XmlElement(name = "Customer")
    public List<UgyvitelCustomer> customer;
}