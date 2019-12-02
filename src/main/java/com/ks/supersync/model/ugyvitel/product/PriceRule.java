package com.ks.supersync.model.ugyvitel.product;

import javax.xml.bind.annotation.*;

public class PriceRule {

    @XmlElement(name = "PriceRuleId")
    public String priceRuleId;
    @XmlElement(name = "PriceRuleType")
    public String priceRuleType;
    @XmlElement(name = "ValidFrom")
    public String validFrom;
    @XmlElement(name = "ValidTo")
    public String validTo;
    @XmlElement(name = "PriceRuleName")
    public String priceRuleName;
    @XmlElement(name = "Currency")
    public String currency;
    @XmlElement(name = "Price")
    public String price;
}