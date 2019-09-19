package com.ks.supersync.service.activationservice;

import org.apache.commons.lang3.RandomStringUtils;

import com.ks.supersync.model.supersync.Result;
import com.ks.supersync.model.supersync.SuperSyncWebshops;
import com.ks.supersync.repository.SuperSyncUserRepository;

import org.springframework.stereotype.Service;

@Service
public class ActivationServiceImpl implements ActivationService {

    private SuperSyncUserRepository superSyncUserRepository;

    public ActivationServiceImpl(SuperSyncUserRepository service){
        this.superSyncUserRepository = service;
    }

    @Override
    public Result enrollUser(String webIdentifier, String webShop, String apiKey) {

        Result result = new Result();

        String password = generateString();

        SuperSyncWebshops superSyncWebshops = new SuperSyncWebshops(webIdentifier, password, apiKey, webShop);

        try {

            superSyncUserRepository.save(superSyncWebshops);

        } catch (Exception e){

            result.isSuccess = false;
            result.Message = "Ez a webshop már létezik az adatbázisban!";
            return result;

        }

        result.Message = password;
        result.isSuccess = true;
        
        return result;
    }

    private static String generateString() {
        return RandomStringUtils.randomAlphanumeric(8);
    }


}