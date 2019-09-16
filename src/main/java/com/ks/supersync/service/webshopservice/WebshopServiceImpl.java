package com.ks.supersync.service.webshopservice;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.ks.supersync.model.UserWebshops;
import com.ks.supersync.repository.SuperSyncUserRepository;
import com.ks.supersync.repository.UserWebshopsRepository;
import com.ks.supersync.service.unasapiservice.UnasApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebshopServiceImpl implements WebshopService {

    private SuperSyncUserRepository superSyncUserRepository;
    private UserWebshopsRepository userWebshopsRepository;

    @Autowired
    UnasApiService unasApiService;

    public WebshopServiceImpl(SuperSyncUserRepository service, UserWebshopsRepository service1){
        this.superSyncUserRepository = service;
        this.userWebshopsRepository = service1;
    }

    @Override
    public Object getItemsForUgyvitel(String webIdentifier, String webPassword, String webShop, String syncType)
            throws IOException, JAXBException {

                UserWebshops uWebshops = userWebshopsRepository.findByUserId(superSyncUserRepository.findByWebIdentifierAndWebPassword(webIdentifier, webPassword).id);

                switch(webShop){
                    case "UNAS":
                    switch(syncType){
                        case "PRODUCT":
                        return unasApiService.getUnasProductsToUgyvitel(uWebshops.webshopApiKey);

                        case "CUSTOMER":
                        return unasApiService.getCustomersToUgyvitel(uWebshops.webshopApiKey);

                        case "ORDER":
                        return unasApiService.getOrdersToUgyvitel(uWebshops.webshopApiKey);
                    }
                }
        return null;
    }

    @Override
    public Object sendItemsFromUgyvitel(String webIdentifier, String webPassword, String webShop, String syncType,
            String Item) throws IOException, JAXBException {

                UserWebshops uWebshops = userWebshopsRepository.findByUserId(superSyncUserRepository.findByWebIdentifierAndWebPassword(webIdentifier, webPassword).id);
       
                switch(webShop){
                    case "UNAS":
                    switch(syncType){
                        case "PRODUCT":
                        return unasApiService.sendUgyvitelProductToUnas(uWebshops.webshopApiKey, Item);

                        case "CUSTOMER":
                        return unasApiService.sendUgyvitelCustomerToUnas(uWebshops.webshopApiKey, Item);

                        case "ORDER":
                        return unasApiService.sendUgyvitelOrderToUnas(uWebshops.webshopApiKey, Item);
                    }
                }
        return null;
    }

}