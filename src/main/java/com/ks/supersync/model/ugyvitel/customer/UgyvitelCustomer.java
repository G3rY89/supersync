package com.ks.supersync.model.ugyvitel.customer;

import javax.xml.bind.annotation.XmlElement;

public class UgyvitelCustomer {

    @XmlElement(name = "LocalId")
    public String localId;
    @XmlElement(name = "CustomerId")
    public String customerId;
    @XmlElement(name = "WebshopId")
    public String webshopId;
    @XmlElement(name = "CustomerCode")
    public String customerCode;
    @XmlElement(name = "CustomerName")
    public String customerName;
    @XmlElement(name = "Email")
    public String email;
    @XmlElement(name = "ContactName")
    public String contactName;
    @XmlElement(name = "CountryCode")
    public String countryCode;
    @XmlElement(name = "CentralAddressName")
    public String centralAddressName;
    @XmlElement(name = "CentralCountry")
    public String centralCountry;
    @XmlElement(name = "CentralZip")
    public String centralZip;
    @XmlElement(name = "CentralCity")
    public String centralCity;
    @XmlElement(name = "CentralStreet")
    public String centralStreet;
    @XmlElement(name = "CentralPublicDomain")
    public String centralPublicDomain;
    @XmlElement(name = "CentralStaircase")
    public String centralStaircase;
    @XmlElement(name = "CentralDoor")
    public String centralDoor;
    @XmlElement(name = "CentralFloor")
    public String centralFloor;
    @XmlElement(name = "CentralNumber")
    public String centralNumber;
    @XmlElement(name = "CentralBuilding")
    public String centralBuilding;
    @XmlElement(name = "DeliveryAddressName")
    public String deliveryAddressName;
    @XmlElement(name = "DeliveryCountry")
    public String deliveryCountry;
    @XmlElement(name = "DeliveryCity")
    public String deliveryCity;
    @XmlElement(name = "DeliveryStreet")
    public String deliveryStreet;
    @XmlElement(name = "DeliveryZip")
    public String deliveryZip;
    @XmlElement(name = "DeliveryPublicDomain")
    public String deliveryPublicDomain;
    @XmlElement(name = "DeliveryStaircase")
    public String deliveryStaircase;
    @XmlElement(name = "DeliveryDoor")
    public String deliveryDoor;
    @XmlElement(name = "DeliveryFloor")
    public String deliveryFloor;
    @XmlElement(name = "DeliveryNumber")
    public String deliveryNumber;
    @XmlElement(name = "DeliveryBuilding")
    public String deliveryBuilding;
    @XmlElement(name = "DeliveryCountryCode")
    public String deliveryCountryCode;
    @XmlElement(name = "OtherAddresses")
    public OtherAddresses otherAddresses;
    @XmlElement(name = "TaxNumber")
    public String taxNumber;
    @XmlElement(name = "EuTaxNumber")
    public String euTaxNumber;
    @XmlElement(name = "Phone")
    public String phone;
    @XmlElement(name = "Fax")
    public String fax;
    @XmlElement(name = "ReceiveNews")
    public String receiveNews;
    @XmlElement(name = "WebPage")
    public String webPage;
    @XmlElement(name = "DiscountPercent")
    public String discountPercent;
    @XmlElement(name = "PaymentmethodName")
    public String paymentmethodName;
    @XmlElement(name = "ObligatoryPaymentterms")
    public String obligatoryPaymentterms;
    @XmlElement(name = "ExpiredDebit")
    public String expiredDebit;
    @XmlElement(name = "Debit")
    public String debit;
    @XmlElement(name = "Active")
    public String active;
    @XmlElement(name = "Comment")
    public String comment;
    @XmlElement(name = "Creditline")
    public String creditline;
    @XmlElement(name = "VatRate")
    public String vatRate;
    @XmlElement(name = "VatCode")
    public String vatCode;
    @XmlElement(name = "Categories")
    public Categories categories;

    public UgyvitelCustomer(){
        this.categories = new Categories();
    }
    
}