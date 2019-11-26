package com.ks.supersync.repository;

import java.util.List;

import com.ks.supersync.model.supersync.SuperSyncWebshops;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SuperSyncUserRepository extends JpaRepository<SuperSyncWebshops, Integer> {
    
    @Transactional
    SuperSyncWebshops findByWebIdentifierAndWebPasswordAndWebshopApiKey(String WebIdentifier, String WebPassword, String ApiKey);
    
    List<SuperSyncWebshops> findByWebIdentifier(String webIdentifier);
    
}