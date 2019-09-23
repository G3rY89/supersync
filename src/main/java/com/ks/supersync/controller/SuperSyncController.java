package com.ks.supersync.controller;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.ks.supersync.service.webshopservice.WebshopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class SuperSyncController {

    @Autowired
    WebshopService webShopService;

    @RequestMapping(value = "/sync_to_ugyvitel", method = RequestMethod.GET, produces = "application/xml")
    public Object syncToUgyvitel(
        @RequestHeader("WebIdentifier") String webIdentifier, 
        @RequestHeader("WebPassword") String webPassword, 
        @RequestHeader("Webshop") String webShop,
        @RequestHeader("SyncType") String syncType) throws IOException, JAXBException{
            return webShopService.getItemsForUgyvitel(webIdentifier, webPassword, webShop, syncType);
    }

    @RequestMapping(value = "/sync_from_ugyvitel", method = RequestMethod.POST, produces = "application/xml", consumes = "application/xml")
    public Object syncFromUgyvitel(
        @RequestHeader("WebIdentifier") String webIdentifier, 
        @RequestHeader("WebPassword") String webPassword, 
        @RequestHeader("Webshop") String webShop,
        @RequestHeader("SyncType") String syncType,
        @RequestBody String item) throws IOException, JAXBException{
            System.out.println(item);
            return webShopService.sendItemsFromUgyvitel(webIdentifier, webPassword, webShop, syncType, item);
    }
}