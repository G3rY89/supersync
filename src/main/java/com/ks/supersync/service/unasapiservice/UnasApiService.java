package com.ks.supersync.service.unasapiservice;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.ks.supersync.model.ugyvitel.customer.UgyvitelCustomers;
import com.ks.supersync.model.ugyvitel.order.UgyvitelOrders;
import com.ks.supersync.model.ugyvitel.product.UgyvitelProducts;
import com.ks.supersync.model.unas.customer.UnasCustomers;


public interface UnasApiService {

    public UgyvitelProducts getUnasProductsToUgyvitel(String apiKey) throws IOException, JAXBException;

    public UgyvitelCustomers getCustomersToUgyvitel(String apiKey) throws IOException, JAXBException;

    public UgyvitelOrders getOrdersToUgyvitel(String apiKey) throws IOException, JAXBException;
    
    public Object sendUgyvitelProductToUnas(String apiKey, String Products) throws IOException, JAXBException;

    public Object sendUgyvitelCustomerToUnas(String apiKey, String Customer) throws IOException, JAXBException;
    
    public Object sendUgyvitelOrderToUnas(String apiKey, String Orders) throws IOException, JAXBException;

    public Object sendUgyvitelStockToUnas(String apiKey, String Products) throws IOException, JAXBException;

    public Object sendUgyvitelProductCategoryToUnas(String apiKey, String Categories) throws IOException, JAXBException;

    public void ValidateUgyvitelCustomersToUnas(UgyvitelCustomers ugyvitelCustomers, UgyvitelCustomers validatedUgyvitelCustomers, UgyvitelCustomers invalidUgyvitelCustomers);
    
    public UnasCustomers mapUgyvitelCustomersToUnasCustomers(UgyvitelCustomers ugyvitelCustomers);
}