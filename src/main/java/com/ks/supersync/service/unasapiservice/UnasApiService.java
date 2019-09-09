package com.ks.supersync.service.unasapiservice;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.ks.supersync.model.ugyvitel.customer.UgyvitelCustomers;
import com.ks.supersync.model.ugyvitel.order.UgyvitelOrders;
import com.ks.supersync.model.ugyvitel.product.UgyvitelProducts;


public interface UnasApiService {

    public UgyvitelProducts getUnasProductsToUgyvitel(String apiKey) throws IOException, JAXBException;

    public UgyvitelCustomers getCustomersToUgyvitel(String apiKey) throws IOException, JAXBException;

    public UgyvitelOrders getOrdersToUgyvitel(String apiKey) throws IOException, JAXBException;
    
    public Object sendUgyvitelProductToUnas(String apiKey, String Products) throws IOException, JAXBException;
}