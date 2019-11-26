package com.ks.supersync.service.webshopservice;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.ks.supersync.model.supersync.SuperSyncWebshops;
import com.ks.supersync.repository.SuperSyncUserRepository;
import com.ks.supersync.service.syncservices.SyncService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebshopServiceImpl implements WebshopService {

    private SuperSyncUserRepository superSyncUserRepository;

    @Autowired
    SyncService unasApiService;

    public WebshopServiceImpl(SuperSyncUserRepository service){
        this.superSyncUserRepository = service;
    }

    @Override
    public Object getItemsForUgyvitel(String webIdentifier, String webPassword, String syncType, String ApiKey)
            throws IOException, JAXBException {

                SuperSyncWebshops uWebshops = superSyncUserRepository.findByWebIdentifierAndWebPasswordAndWebshopApiKey(webIdentifier, webPassword, ApiKey);

        if (uWebshops != null) {
            switch (uWebshops.webshopName) {
            case "UNAS":
                switch (syncType) {
                case "PRODUCT":
                    return unasApiService.getProductsToUgyvitel(uWebshops.webshopApiKey);

                case "CUSTOMER":
                    return unasApiService.getCustomersToUgyvitel(uWebshops.webshopApiKey);

                case "ORDER":
                    return unasApiService.getOrdersToUgyvitel(uWebshops.webshopApiKey);
                }
            }
        }

        return null;
    }

    @Override
    public Object sendItemsFromUgyvitel(String webIdentifier, String webPassword, String syncType,
            String ApiKey, String Item) throws IOException, JAXBException {

        SuperSyncWebshops uWebshops = superSyncUserRepository
                .findByWebIdentifierAndWebPasswordAndWebshopApiKey(webIdentifier, webPassword, ApiKey);
            if(uWebshops != null){
                switch(uWebshops.webshopName){
                    case "UNAS":
                    switch(syncType){
                        case "PRODUCT":
                        return unasApiService.sendUgyvitelProducts(uWebshops.webshopApiKey, Item);

                        case "CUSTOMER":
                        return unasApiService.sendUgyvitelCustomers(uWebshops.webshopApiKey, Item);

                        case "ORDER":
                        return unasApiService.sendUgyvitelOrders(uWebshops.webshopApiKey, Item);

                        case "STOCK":
                        return unasApiService.sendUgyvitelStocks(uWebshops.webshopApiKey, Item);

                        case "PRODUCT_CATEGORY":
                        return unasApiService.sendUgyvitelProductCategories(uWebshops.webshopApiKey, Item);
                    }
                }
            }
        return null;
    }

}