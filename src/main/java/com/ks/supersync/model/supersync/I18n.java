package com.ks.supersync.model.supersync;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "I18n")
public class I18n {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    public Integer id;
    @Column(nullable = false, unique = false)
    public String currency;
    @Column(nullable = false, unique = false)
    public String language;
}