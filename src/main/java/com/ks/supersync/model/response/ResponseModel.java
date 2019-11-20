package com.ks.supersync.model.response;

import javax.xml.bind.annotation.*;

public class ResponseModel {

    @XmlElement(name = "Action")
    public String action;
    @XmlElement(name = "Id")
    public String id;
    @XmlElement(name = "Status")
    public String status;
    @XmlElement(name = "Error")
    public String error;
    @XmlElement(name = "Email")
    public String email;
}