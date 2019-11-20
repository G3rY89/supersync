package com.ks.supersync.model.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Customers")
public class CustomerErrorResponse{

    @XmlElement(name = "Customer")
    public List<ResponseModel> customer;

    public CustomerErrorResponse(){
        this.customer = new ArrayList<>();
    }
}