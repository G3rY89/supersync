package com.ks.supersync.model.supersync;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "webshops")
public class SuperSyncWebshops{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    @Column(nullable = false, unique = false)
    public String webIdentifier;
    @Column(nullable = false, unique = true)
    public String webPassword;
    @Column(nullable = false, unique = false)
    public String webshopName;
    @Column(nullable = true, unique = true)
    public String webshopApiKey;
    @Column(nullable = true, unique = true)
    public String webshopUsername;
    @Column(nullable = true, unique = true)
    public String webshopPassword;

    public SuperSyncWebshops(String webIdentifier, String webPassword, String webshopApikey, String webshopName){
        this.webIdentifier = webIdentifier;
        this.webPassword = webPassword;
        this.webshopApiKey = webshopApikey;
        this.webshopName = webshopName;
    }

    public SuperSyncWebshops(String webIdentifier, String webPassword, String webshopName, String webshopUsername, String webshopPassword){
        this.webIdentifier = webIdentifier;
        this.webPassword = webPassword;
        this.webshopName = webshopName;
        this.webshopUsername = webshopUsername;
        this.webshopPassword = webshopPassword;
    }

    public SuperSyncWebshops(){

    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the userId
     */
    public String getwebIdentifier() {
        return webIdentifier;
    }

    /**
     * @return the webshopApiKey
     */
    public String getWebshopApiKey() {
        return webshopApiKey;
    }

    /**
     * @return the webshopName
     */
    public String getWebshopName() {
        return webshopName;
    }

    /**
     * @return the webshopPassword
     */
    public String getWebshopPassword() {
        return webshopPassword;
    }

    /**
     * @return the webshopUsername
     */
    public String getWebshopUsername() {
        return webshopUsername;
    }
    
    
}