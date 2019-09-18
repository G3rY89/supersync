package com.ks.supersync.model.ugyvitel.customer;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class OtherAddresses {

    @XmlElement(name = "OtherAddress")
    public List<OtherAddress> otherAddress;
}