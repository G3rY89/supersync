package com.ks.supersync.model.ugyvitel.category;

import javax.xml.bind.annotation.XmlElement;

public class UgyvitelCategory {

    @XmlElement(name = "Name")
    public String name;
    @XmlElement(name = "CT_Id")
    public String ct_Id;
    @XmlElement(name = "CF_FieldName")
    public String cf_FieldName;
    @XmlElement(name = "CategoryOrder")
    public String categoryOrder;
    @XmlElement(name = "Visible")
    public String visible;
    @XmlElement(name = "Level")
    public String level;
    @XmlElement(name = "Left")
    public String left;
    @XmlElement(name = "Right")
    public String right;
    @XmlElement(name = "Id")
    public String id;
    @XmlElement(name = "ParentId")
    public String parentId;
    @XmlElement(name = "Tree")
    public String tree;
}