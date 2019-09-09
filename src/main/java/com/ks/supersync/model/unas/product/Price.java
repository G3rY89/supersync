package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

public class Price {

    @XmlElement(name = "Type")
    public String type;
    @XmlElement(name = "Net")
    public String net;
    @XmlElement(name = "Gross")
    public String gross;
    @XmlElement(name = "Start")
    public String start;
    @XmlElement(name = "End")
    public String end;
    @XmlElement(name = "Group")
    public String group;
    @XmlElement(name = "GroupName")
    public String groupName;
    @XmlElement(name = "Area")
    public String area;
    @XmlElement(name = "AreaName")
    public String areaName;
    @XmlElement(name = "SaleNet")
    public Integer saleNet;
    @XmlElement(name = "SaleGross")
    public Integer saleGross;
    @XmlElement(name = "SaleStart")
    public String saleStart;
    @XmlElement(name = "SaleEnd")
    public String saleEnd;
    @XmlElement(name = "Percent")
    public String percent;

}