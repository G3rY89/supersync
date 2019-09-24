package com.ks.supersync.model.supersync;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "processes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Processes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    public Integer id;
    @Column(nullable = false, unique = true)
    @XmlTransient
    public String apiKey;
    @Column(nullable = false, unique = true)
    @XmlElement(name = "DownloadProduct")
    public Integer getProduct;
    @Column(nullable = false, unique = true)
    @XmlElement(name = "UploadProduct")
    public Integer setProduct;
    @Column(nullable = false, unique = true)
    @XmlElement(name = "DownloadCustomer")
    public Integer getCustomer;
    @Column(nullable = false, unique = true)
    @XmlElement(name = "UploadCustomer") 
    public Integer setCustomer;
    @Column(nullable = false, unique = true)
    @XmlElement(name = "DownloadOrder")
    public Integer getOrder;
    @Column(nullable = false, unique = true)
    @XmlElement(name = "UploadOrder")
    public Integer setOrder;
    @Column(nullable = false, unique = true)
    @XmlElement(name = "DownloadProductCategories")
    public Integer getProductCategories;
    @Column(nullable = false, unique = true)
    @XmlElement(name = "DownloadStock")
    public Integer getStock;

}