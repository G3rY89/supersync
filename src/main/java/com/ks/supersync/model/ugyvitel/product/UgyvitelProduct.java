package com.ks.supersync.model.ugyvitel.product;

import javax.xml.bind.annotation.*;

import org.jetbrains.annotations.Nullable;


public class UgyvitelProduct {

    @XmlElement(name = "ProductId")
    public int productId;
    @XmlElement(name = "WebshopId")
    public Integer webshopId;
    @XmlElement(name = "ProductCode")
    public String productCode;
    @XmlElement(name = "Name")
    public ProductName productName;
    @XmlElement(name = "ItemNumber")
    public String itemNumber;
    @XmlElement(name = "Comment")
    @Nullable
    public Comment comment;
    @XmlElement(name = "Barcode")
    public String barCode;
    @XmlElement(name = "LastPurchasePrice")
    public Integer lastPurchasePrice;
    @XmlElement(name = "Active")
    public Integer active;
    @XmlElement(name = "VatCode")
    public String vatCode;
    @XmlElement(name = "QuantityUnit")
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

    public UgyvitelProduct(){
        this.productName = new ProductName();
        this.comment = new Comment();
        this.quantityUnit = new QuantityUnit();
        this.priceRules = new PriceRules();
        this.productName = new ProductName();
    }

    @Override
    public String toString() { 
        return String.format(productId + " " + webshopId); 
    } 

}