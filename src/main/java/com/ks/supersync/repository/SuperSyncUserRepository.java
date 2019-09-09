package com.ks.supersync.repository;

import com.ks.supersync.model.SuperSyncUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperSyncUserRepository extends JpaRepository<SuperSyncUser, Integer> {

    SuperSyncUser findByWebIdentifierAndWebPassword(String WebIdentifier, String WebPassword);
    
}