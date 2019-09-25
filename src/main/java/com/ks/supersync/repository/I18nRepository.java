package com.ks.supersync.repository;

import com.ks.supersync.model.supersync.I18n;

import org.springframework.data.jpa.repository.JpaRepository;

public interface I18nRepository extends JpaRepository<I18n, Integer> {

    I18n findByWebshopApiKey(String webshopApiKey);
}