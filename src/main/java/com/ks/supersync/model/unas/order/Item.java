package com.ks.supersync.model.unas.order;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Item {

    @XmlElement(name = "Id")
    public String id;
    @XmlElement(name = "Sku")
    public String sku;
    @XmlElement(name = "Name")
    public String name;
    @XmlElement(name = "Unit")
    public String unit;
    @XmlElement(name = "Quantity")
    public String quantity;
    @XmlElement(name = "PriceNet")
    public String priceNet;
    @XmlElement(name = "PriceGross")
    public String priceGross;
    @XmlElement(name = "Vat")
    public String vat;
    @XmlElement(name = "Status")
    public String status;
    @XmlElement(name = "PlusStatuses")
    public List<Status> plusStatuses;
    @XmlElement(name = "Control")
    public Control control;
    @XmlElement(name = "Variants")
    public List<Variant> variants;
    @XmlElement(name = "ProductParams")
    public List<ProductParam> productParams;
    @XmlElement(name = "Percent")
    public String percent;
}