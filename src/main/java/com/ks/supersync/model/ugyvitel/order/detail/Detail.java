package com.ks.supersync.model.ugyvitel.order.detail;

import javax.xml.bind.annotation.XmlElement;

public class Detail {

    @XmlElement(name = "WebshopId")
    public String webshopId;
    @XmlElement(name = "ProductCode")
    public String productCode;
    @XmlElement(name = "ProductName")
    public String productName;
    @XmlElement(name = "Quantity")
    public String quantity;
    @XmlElement(name = "QuantityUnit")
    public String quantityUnit;
    @XmlElement(name = "UnitPrice")
    public String unitPrice;
    @XmlElement(name = "Stock")
    public String stock;
    @XmlElement(name = "ProductComment")
    public String productComment;
    @XmlElement(name = "VatCode")
    public String vatCode;

}