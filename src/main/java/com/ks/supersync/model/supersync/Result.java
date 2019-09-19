package com.ks.supersync.model.supersync;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Result")
public class Result {

    @XmlElement(name = "IsSuccess")
    public boolean isSuccess;
    @XmlElement(name = "Message")
    public String Message;
}