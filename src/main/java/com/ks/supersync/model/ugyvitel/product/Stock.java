package com.ks.supersync.model.ugyvitel.product;

import javax.xml.bind.annotation.XmlElement;

public class Stock {

    @XmlElement(name = "StockId")
    public String stockId;
    @XmlElement(name = "StockName")
    public String stockName;
    @XmlElement(name = "TotalStock")
    public String totalStock;
    @XmlElement(name = "ReservedStock")
    public String reservedStock;
    @XmlElement(name = "FreeStock")
    public Float freeStock;
    @XmlElement(name = "IncommingQuantity")
    public String incommingQuantity;
    @XmlElement(name = "IncommingStockDate")
    public String incommingStockDate;
}