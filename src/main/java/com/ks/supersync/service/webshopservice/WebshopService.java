package com.ks.supersync.service.webshopservice;

import java.io.IOException;

import javax.xml.bind.JAXBException;

public interface WebshopService {

    Object getItemsForUgyvitel(String webIdentifier, String webPassword, String syncType, String ApiKey) throws IOException, JAXBException;

    Object sendItemsFromUgyvitel(String webIdentifier, String webPassword, String syncType, String ApiKey, String Item) throws IOException, JAXBException;

}