package com.ks.supersync.model.ugyvitel.product;

import javax.xml.bind.annotation.*;


public class UgyvitelProduct {

    @XmlElement(name = "ProductId")
    public int productId;
    @XmlElement(name = "WebshopId")
    public Integer webshopId;
    @XmlElement(name = "ProductCode")
    public String productCode;
    @XmlElement(name = "Name")
    public Name productName;
    @XmlElement(name = "ItemNumber")
    public String itemNumber;
    @XmlElement(name = "Comment")
    public Comment comment;
    @XmlElement(name = "Barcode")
    public String barCode;
    @XmlElement(name = "LastPurchasePrice")
    public String lastPurchasePrice;
    @XmlElement(name = "Active")
    public Integer active;
    @XmlElement(name = "VatCode")
    public String vatCode;
    @XmlElement(name = "QuantityUnits")
    public QuantityUnit quantityUnit;
    @XmlElement(name = "Service")
    public Integer service;
    @XmlElement(name = "UnitPrice")
    public String unitPrice;
    @XmlElement(name = "DiscountPrice")
    public String discountPrice;
    @XmlElement(name = "VatRate")
    public String vatRate;
    @XmlElement(name = "PriceRules")
    public PriceRules priceRules;
    @XmlElement(name = "Stocks")
    public Stocks stocks;
    @XmlElement(name = "Categories")
    public Categories categories;

    public UgyvitelProduct(){
        this.productName = new Name();
        this.comment = new Comment();
        this.quantityUnit = new QuantityUnit();
        this.priceRules = new PriceRules();
        this.stocks = new Stocks();
        this.categories = new Categories();
    }

    @Override
    public String toString() { 
        return String.format(productId + " " + webshopId); 
    } 

}