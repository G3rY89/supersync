package com.ks.supersync.model.unas.customer;

import javax.xml.bind.annotation.XmlElement;

public class Others {

    @XmlElement(name = "FacebookConnect")
    public String facebookConnect;
    @XmlElement(name = "Ip")
    public String ip;
    @XmlElement(name = "Referer")
    public String referer;
    
}