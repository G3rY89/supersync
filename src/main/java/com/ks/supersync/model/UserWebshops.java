package com.ks.supersync.model;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "webshops")
public class UserWebshops{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    @Column(nullable = false, unique = true)
    public Integer userId;
    @Column(nullable = false, unique = true)
    public String webshopName;
    @Column(nullable = true, unique = true)
    public String webshopApiKey;
    @Column(nullable = true, unique = true)
    public String webshopUsername;
    @Column(nullable = true, unique = true)
    public String webshopPassword;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
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