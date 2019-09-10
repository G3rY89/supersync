package com.ks.supersync.repository;

import com.ks.supersync.model.SuperSyncUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SuperSyncUserRepository extends JpaRepository<SuperSyncUser, Integer> {
    
    @Transactional
    SuperSyncUser findByWebIdentifierAndWebPassword(String WebIdentifier, String WebPassword);
    
}