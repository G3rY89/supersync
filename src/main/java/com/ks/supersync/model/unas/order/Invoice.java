package com.ks.supersync.model.unas.order;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "Invoice")
public class Invoice {

    @XmlElement(name = "Status")
    public String status;
    @XmlElement(name = "StatusText")
    public String statusText;
    @XmlElement(name = "Number")
    public String number;
}