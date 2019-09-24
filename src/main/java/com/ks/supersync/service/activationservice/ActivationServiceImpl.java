package com.ks.supersync.service.activationservice;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

import com.ks.supersync.model.supersync.Processes;
import com.ks.supersync.model.supersync.Result;
import com.ks.supersync.model.supersync.SuperSyncWebshops;
import com.ks.supersync.repository.ProcessRepository;
import com.ks.supersync.repository.SuperSyncUserRepository;

import org.springframework.stereotype.Service;

@Service
public class ActivationServiceImpl implements ActivationService {

    private SuperSyncUserRepository superSyncUserRepository;
    private ProcessRepository processRepository;

    public ActivationServiceImpl(SuperSyncUserRepository service, ProcessRepository service1){
        this.superSyncUserRepository = service;
        this.processRepository = service1;
    }

    @Override
    public Result enrollUser(String webIdentifier, String webShop, String apiKey) {

        Result result = new Result();

        String password = generateString();

        List<SuperSyncWebshops> existingSuperSyncWebshops = superSyncUserRepository.findByWebIdentifier(webIdentifier);

        for (SuperSyncWebshops superSyncWebshop : existingSuperSyncWebshops) {
            if(superSyncWebshop.webshopApiKey.equals(apiKey)){
                result.isSuccess = false;
                result.Message = "Ez a webshop már létezik az adatbázisban!";
                return result;
            }   
        }

        SuperSyncWebshops newSuperSyncWebshops = new SuperSyncWebshops(webIdentifier, password, apiKey, webShop);
        
        Processes processes = new Processes(apiKey);

        try {

            superSyncUserRepository.save(newSuperSyncWebshops);
            processRepository.save(processes);

        } catch (Exception e){

            result.isSuccess = false;
            result.Message = "Hiba";
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