package com.ks.supersync.repository;

import com.ks.supersync.model.UserWebshops;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWebshopsRepository extends JpaRepository<UserWebshops, Integer> {

    UserWebshops findByUserId(int id);

}