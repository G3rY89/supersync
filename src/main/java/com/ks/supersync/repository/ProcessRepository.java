package com.ks.supersync.repository;

import com.ks.supersync.model.supersync.Processes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRepository extends JpaRepository<Processes, Integer>{

    Processes findByapiKey(String apiKey);

}