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
@XmlRootElement(name = "SyncProcesses")
public class Processes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    public Integer id;
    @Column(nullable = false, unique = false)
    @XmlTransient
    public String apiKey;
    @Column(nullable = false, unique = false)
    @XmlElement(name = "DownloadProduct")
    public Integer getProduct;
    @Column(nullable = false, unique = false)
    @XmlElement(name = "UploadProduct")
    public Integer setProduct;
    @Column(nullable = false, unique = false)
    @XmlElement(name = "DownloadCustomer")
    public Integer getCustomer;
    @Column(nullable = false, unique = false)
    @XmlElement(name = "UploadCustomer") 
    public Integer setCustomer;
    @Column(nullable = false, unique = false)
    @XmlElement(name = "DownloadOrder")
    public Integer getOrder;
    @Column(nullable = false, unique = false)
    @XmlElement(name = "UploadOrder")
    public Integer setOrder;
    @Column(nullable = false, unique = false)
    @XmlElement(name = "UploadProductCategories")
    public Integer setProductCategories;
    @Column(nullable = false, unique = false)
    @XmlElement(name = "UploadStock")
    public Integer setStock;

    public Processes(String apiKey){
        this.apiKey = apiKey;
        this.getProduct = 0;
        this.setProduct = 0;
        this.getCustomer = 0;
        this.setCustomer = 0;
        this.getOrder = 0;
        this.setOrder = 0;
        this.setProductCategories = 0;
        this.setStock = 0;
    }

    public Processes(){

    }

}