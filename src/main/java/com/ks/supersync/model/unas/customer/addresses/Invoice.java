package com.ks.supersync.model.unas.customer.addresses;

import javax.xml.bind.annotation.XmlElement;

public class Invoice {
    
    @XmlElement(name = "Name")
    public String name;
    @XmlElement(name = "ZIP")
    public String ZIP;
    @XmlElement(name = "City")
    public String city;
    @XmlElement(name = "Street")
    public String street;
    @XmlElement(name = "StreetName")
    public String streetName;
    @XmlElement(name = "StreetType")
    public String streetType;
    @XmlElement(name = "StreetNumber")
    public String streetNumber;
    @XmlElement(name = "County")
    public String county;
    @XmlElement(name = "Country")
    public String country;
    @XmlElement(name = "CountryCode")
    public String countryCode;
    @XmlElement(name = "TaxNumber")
    public String taxNumber;
    @XmlElement(name = "EUTaxNumber")
    public String euTaxNumber;
    
    
}