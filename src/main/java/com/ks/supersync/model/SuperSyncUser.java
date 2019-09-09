package com.ks.supersync.model;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "users")
public class SuperSyncUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    @Column(nullable = false, unique = true)
    public String webIdentifier;
    @Column(nullable = false, unique = true)
    public String webPassword;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the webIdentifier
     */
    public String getWebIdentifier() {
        return webIdentifier;
    }

    /**
     * @return the webPassword
     */
    public String getWebPassword() {
        return webPassword;
    }

}
