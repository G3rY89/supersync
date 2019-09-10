package com.ks.supersync.repository;

import com.ks.supersync.model.UserWebshops;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserWebshopsRepository extends JpaRepository<UserWebshops, Integer> {

    @Transactional
    UserWebshops findByUserId(int id);

}