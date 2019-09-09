package com.ks.supersync.model.unas.order;

import javax.xml.bind.annotation.*;

@XmlType(namespace = "Shipping")
public class Shipping {

    @XmlElement(name = "Id")
    public String id;
    @XmlElement(name = "Name")
    public String name;
    @XmlElement(name = "PackageNumber")
    public String packageNumber;
    @XmlElement(name = "ForeignID")
    public String foreignID;
    
}