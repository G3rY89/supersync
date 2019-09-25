package com.ks.supersync.service.activationservice;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

import com.ks.supersync.model.supersync.I18n;
import com.ks.supersync.model.supersync.Processes;
import com.ks.supersync.model.supersync.Result;
import com.ks.supersync.model.supersync.SuperSyncWebshops;
import com.ks.supersync.repository.I18nRepository;
import com.ks.supersync.repository.ProcessRepository;
import com.ks.supersync.repository.SuperSyncUserRepository;

import org.springframework.stereotype.Service;

@Service
public class ActivationServiceImpl implements ActivationService {

    private SuperSyncUserRepository superSyncUserRepository;
    private ProcessRepository processRepository;
    private I18nRepository i18nRepository;

    public ActivationServiceImpl(SuperSyncUserRepository service, ProcessRepository pService, I18nRepository iService){
        this.superSyncUserRepository = service;
        this.processRepository = pService;
        this.i18nRepository = iService;
    }

    @Override
    public Result enrollUser(String webIdentifier, String webShop, String apiKey, String currency, String language) {

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
        
        Processes newProcesses = new Processes(apiKey);

        I18n newI18n = new I18n(apiKey, currency, language);

        try {

            superSyncUserRepository.save(newSuperSyncWebshops);
            processRepository.save(newProcesses);
            i18nRepository.save(newI18n);

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