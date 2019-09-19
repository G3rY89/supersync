package com.ks.supersync.repository;

import com.ks.supersync.model.supersync.SuperSyncWebshops;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SuperSyncUserRepository extends JpaRepository<SuperSyncWebshops, Integer> {
    
    @Transactional
    SuperSyncWebshops findByWebIdentifierAndWebPassword(String WebIdentifier, String WebPassword);
    
}