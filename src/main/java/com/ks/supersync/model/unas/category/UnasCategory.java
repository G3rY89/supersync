package com.ks.supersync.model.unas.category;

import javax.xml.bind.annotation.XmlElement;

public class UnasCategory {

    @XmlElement(name = "Action")
    public String action;
    @XmlElement(name = "Id")
    public String id;
    @XmlElement(name = "Name")
    public String name;
    @XmlElement(name = "Url")
    public String url;
    @XmlElement(name = "SefUrl")
    public String sefUrl;
    @XmlElement(name = "AltUrl")
    public String altUrl;
    @XmlElement(name = "AltUrlBlank")
    public String altUrlBlank;
    @XmlElement(name = "Display")
    public Display display;
    @XmlElement(name = "PageLayout")
    public PageLayout pageLayout;
    @XmlElement(name = "Parent")
    public Parent parent;
    @XmlElement(name = "Order")
    public String order;
    @XmlElement(name = "Products")
    public Products products;
    @XmlElement(name = "Texts")
    public Texts texts;
    @XmlElement(name = "Meta")
    public Meta meta;
    @XmlElement(name = "Image")
    public Image image;
}