package com.ks.supersync.model.ugyvitel.order;


import javax.xml.bind.annotation.XmlElement;

import com.ks.supersync.model.ugyvitel.order.detail.Details;

public class UgyvitelOrder {

    @XmlElement(name = "WebshopId")
    public String webshopId;
    @XmlElement(name = "Currency")
    public String currency;
    @XmlElement(name = "CurrencyRate")
    public String currencyRate;
    @XmlElement(name = "CustomerWebshopId")
    public String customerWebshopId;
    @XmlElement(name = "CustomerCode")
    public String customerCode;
    @XmlElement(name = "Phone")
    public String Phone;
    @XmlElement(name = "Email")
    public String Email;
    @XmlElement(name = "CustomerName")
    public String customerName;
    @XmlElement(name = "CustomerContact")
    public String customerContact;
    @XmlElement(name = "InvoicingZip")
    public String invoicingZip;
    @XmlElement(name = "InvoicingCity")
    public String invoicingCity;
    @XmlElement(name = "InvoicingStreet")
    public String invoicingStreet;
    @XmlElement(name = "InvoicingStaircase")
    public String invoicingStaircase;
    @XmlElement(name = "InvoicingDoor")
    public String invoicingDoor;
    @XmlElement(name = "InvoicingFloor")
    public String invoicingFloor;
    @XmlElement(name = "InvoicingBuilding")
    public String invoicingBuilding;
    @XmlElement(name = "InvoicingNumber")
    public String invoicingNumber;
    @XmlElement(name = "InvoicingPublicDomain")
    public String invoicingPublicDomain;
    @XmlElement(name = "InvoicingCountry")
    public String invoicingCountry;
    @XmlElement(name = "DeliveryName")
    public String deliveryName;
    @XmlElement(name = "DeliveryZip")
    public String deliveryZip;
    @XmlElement(name = "DeliveryCity")
    public String deliveryCity;
    @XmlElement(name = "DeliveryStreet")
    public String deliveryStreet;
    @XmlElement(name = "DeliveryStaircase")
    public String deliveryStaircase;
    @XmlElement(name = "DeliveryDoor")
    public String deliveryDoor;
    @XmlElement(name = "DeliveryFloor")
    public String deliveryFloor;
    @XmlElement(name = "DeliveryBuilding")
    public String deliveryBuilding;
    @XmlElement(name = "DeliveryNumber")
    public String deliveryNumber;
    @XmlElement(name = "DeliveryPublicDomain")
    public String deliveryPublicDomain;
    @XmlElement(name = "DeliveryCountry")
    public String deliveryCountry;
    @XmlElement(name = "TaxNumber")
    public String taxNumber;
    @XmlElement(name = "SequencePrefix")
    public String sequencePrefix;
    @XmlElement(name = "paymentDelayDays")
    public String paymentDelayDays;
    @XmlElement(name = "Paymentmethod")
    public String paymentmethod;
    @XmlElement(name = "DeliveryDate")
    public String deliveryDate;
    @XmlElement(name = "OrderDate")
    public String orderDate;
    @XmlElement(name = "TransportMode")
    public String transportMode;
    @XmlElement(name = "TopComment")
    public String topComment;
    @XmlElement(name = "BottomComment")
    public String bottomComment;
    @XmlElement(name = "Details")
    public Details details;
    
}