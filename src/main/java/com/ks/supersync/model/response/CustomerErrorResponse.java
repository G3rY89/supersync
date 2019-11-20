package com.ks.supersync.model.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

import com.ks.supersync.model.unas.customer.UnasCustomers;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Customers")
@XmlSeeAlso({UnasCustomers.class})
public class CustomerErrorResponse{

    @XmlElement(name = "Customer")
    public List<ResponseModel> customer;

    public CustomerErrorResponse(){
        this.customer = new ArrayList<>();
    }
}