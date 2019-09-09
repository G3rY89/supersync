package com.ks.supersync.model.unas.product;

import javax.xml.bind.annotation.*;

import org.jetbrains.annotations.Nullable;

public class Description {

    @XmlElement(name = "Short")
    @Nullable public String shortDesc;
    @XmlElement(name = "Long")
    public String longDesc;
}