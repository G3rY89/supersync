package com.ks.supersync.service.syncservices;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.ks.supersync.model.ugyvitel.customer.UgyvitelCustomers;
import com.ks.supersync.model.ugyvitel.order.UgyvitelOrders;
import com.ks.supersync.model.ugyvitel.product.UgyvitelProducts;


public interface SyncService {

    public UgyvitelProducts getProductsToUgyvitel(String apiKey) throws IOException, JAXBException;

    public UgyvitelCustomers getCustomersToUgyvitel(String apiKey) throws IOException, JAXBException;

    public UgyvitelOrders getOrdersToUgyvitel(String apiKey) throws IOException, JAXBException;
    
    public Object sendUgyvitelProducts(String apiKey, String Products) throws IOException, JAXBException;

    public Object sendUgyvitelCustomers(String apiKey, String Customer) throws IOException, JAXBException;
    
    public Object sendUgyvitelOrders(String apiKey, String Orders) throws IOException, JAXBException;

    public Object sendUgyvitelStocks(String apiKey, String Products) throws IOException, JAXBException;

    public Object sendUgyvitelProductCategories(String apiKey, String Categories) throws IOException, JAXBException;
}