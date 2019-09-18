package com.ks.supersync.model.ugyvitel.customer;

import javax.xml.bind.annotation.XmlElement;

public class OtherAddress {

    @XmlElement(name = "OtherAddressName")
    public String otherAddressName;
    @XmlElement(name = "OtherCountry")
    public String otherCountry;
    @XmlElement(name = "OtherZip")
    public String otherZip;
    @XmlElement(name = "OtherCity")
    public String otherCity;
    @XmlElement(name = "OtherStreet")
    public String otherStreet;
    @XmlElement(name = "OtherPublicDomain")
    public String otherPublicDomain;
    @XmlElement(name = "OtherStaircase")
    public String otherStaircase;
    @XmlElement(name = "OtherDoor")
    public String otherDoor;
    @XmlElement(name = "OtherFloor")
    public String otherFloor;
    @XmlElement(name = "OtherNumber")
    public String otherNumber;
    @XmlElement(name = "OtherBuilding")
    public String otherBuilding;
}