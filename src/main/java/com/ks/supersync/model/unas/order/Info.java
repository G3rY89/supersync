package com.ks.supersync.model.unas.order;

import javax.xml.bind.annotation.XmlElement;

public class Info {

    @XmlElement(name = "MergedFrom")
    public MergedFrom mergedFrom;
    @XmlElement(name = "SeparatedTo")
    public SeparatedTo separatedTo;
    @XmlElement(name = "SeparatedFrom")
    public SeparatedFrom separatedFrom;
}