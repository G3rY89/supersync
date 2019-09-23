package com.ks.supersync.service.unasapiservice;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.ks.supersync.model.ugyvitel.category.UgyvitelCategories;
import com.ks.supersync.model.ugyvitel.category.UgyvitelCategory;
import com.ks.supersync.model.ugyvitel.customer.OtherAddress;
import com.ks.supersync.model.ugyvitel.customer.OtherAddresses;
import com.ks.supersync.model.ugyvitel.customer.UgyvitelCustomer;
import com.ks.supersync.model.ugyvitel.customer.UgyvitelCustomers;
import com.ks.supersync.model.ugyvitel.order.UgyvitelOrder;
import com.ks.supersync.model.ugyvitel.order.UgyvitelOrders;
import com.ks.supersync.model.ugyvitel.order.detail.Detail;
import com.ks.supersync.model.ugyvitel.order.detail.Details;
import com.ks.supersync.model.ugyvitel.product.PriceRule;
import com.ks.supersync.model.ugyvitel.product.Stock;
import com.ks.supersync.model.ugyvitel.product.UgyvitelProduct;
import com.ks.supersync.model.ugyvitel.product.UgyvitelProducts;
import com.ks.supersync.model.unas.category.UnasCategories;
import com.ks.supersync.model.unas.category.UnasCategory;
import com.ks.supersync.model.unas.customer.UnasCustomer;
import com.ks.supersync.model.unas.customer.UnasCustomers;
import com.ks.supersync.model.unas.customer.addresses.Other;
import com.ks.supersync.model.unas.order.Comment;
import com.ks.supersync.model.unas.order.Item;
import com.ks.supersync.model.unas.order.UnasOrder;
import com.ks.supersync.model.unas.order.UnasOrders;
import com.ks.supersync.model.unas.product.Category;
import com.ks.supersync.model.unas.product.Description;
import com.ks.supersync.model.unas.product.Price;
import com.ks.supersync.model.unas.product.Prices;
import com.ks.supersync.model.unas.product.UnasProduct;
import com.ks.supersync.model.unas.product.UnasProducts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import okhttp3.*;

@Service
public class UnasApiServiceImpl implements UnasApiService {

  OkHttpClient client = new OkHttpClient();

  @Value("${unasapi.serviceurl}")
  private String unasapiServiceUrl;

  @Override
  public UgyvitelProducts getUnasProductsToUgyvitel(String apiKey) throws IOException, JAXBException {
    
    UgyvitelProducts uProducts = new UgyvitelProducts();

    client.newBuilder().connectTimeout(15, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS);

    Request request = new Request.Builder()
      .url(unasapiServiceUrl + UnasMServiceEndpoints.GET_PRODUCTS.toString() + "?debug=true")
      .get()
      .addHeader("ApiKey", apiKey)
      .build();
    Response response = client.newCall(request).execute();

    JAXBContext jaxbContext = JAXBContext.newInstance(UnasProducts.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    StringReader reader = new StringReader(response.body().string());
    
    UnasProducts unasProducts = (UnasProducts) jaxbUnmarshaller.unmarshal(reader);

    uProducts = mapUnasProductsToUgyvitelProducts(unasProducts);
    
    return uProducts;
  }

  @Override
  public UgyvitelCustomers getCustomersToUgyvitel(String apiKey) throws IOException, JAXBException {

    UgyvitelCustomers uCustomers = new UgyvitelCustomers();

      Request request = new Request.Builder()
        .url(unasapiServiceUrl + UnasMServiceEndpoints.GET_CUSTOMERS.toString())
        .get()
        .addHeader("ApiKey", apiKey)
        .build();
      Response response = client.newCall(request).execute();

      JAXBContext jaxbContext = JAXBContext.newInstance(UnasCustomers.class);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      StringReader reader = new StringReader(response.body().string());

      UnasCustomers unasCustomers = (UnasCustomers) jaxbUnmarshaller.unmarshal(reader);

      uCustomers = mapUnasCustomersToUgyvitelCustomers(unasCustomers);

    return uCustomers;
  }

  @Override
  public UgyvitelOrders getOrdersToUgyvitel(String apiKey) throws IOException, JAXBException {

    UgyvitelOrders uOrders = new UgyvitelOrders();

      Request request = new Request.Builder()
        .url(unasapiServiceUrl + UnasMServiceEndpoints.GET_ORDERS.toString())
        .get()
        .addHeader("ApiKey", apiKey)
        .build();
      Response response = client.newCall(request).execute();

      JAXBContext jaxbContext = JAXBContext.newInstance(UnasOrders.class);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      StringReader reader = new StringReader(response.body().string());

      UnasOrders unasOrders = (UnasOrders) jaxbUnmarshaller.unmarshal(reader);

      uOrders = mapUnasOrdersToUgyvitelOrders(unasOrders);
      
      return uOrders;
}

  @Override
  public Object sendUgyvitelProductToUnas(String apiKey, String Products) throws IOException, JAXBException {
   
      JAXBContext jaxbContext = JAXBContext.newInstance(UgyvitelProducts.class);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      StringReader reader = new StringReader(Products);

      UgyvitelProducts ugyvitelProducts = (UgyvitelProducts) jaxbUnmarshaller.unmarshal(reader);

      MediaType mediaType = MediaType.parse("application/xml");

      jaxbContext = JAXBContext.newInstance(UnasProducts.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      StringWriter sw = new StringWriter();
      jaxbMarshaller.marshal(mapUgyvitelProductsToUnasProducts(ugyvitelProducts), sw);

      RequestBody body = RequestBody.create(mediaType, sw.toString());

      Request setProductRequest = new Request.Builder()
          .url(unasapiServiceUrl + UnasMServiceEndpoints.SET_PRODUCTS.toString())
          .post(body)
          .addHeader("ApiKey", apiKey)
          .build();
      Response response = client.newCall(setProductRequest).execute();

    return response.body().string();
  }

  @Override
  public Object sendUgyvitelCustomerToUnas(String apiKey, String Customers) throws IOException, JAXBException {
    
    JAXBContext jaxbContext = JAXBContext.newInstance(UgyvitelCustomers.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    StringReader reader = new StringReader(Customers);

    UgyvitelCustomers ugyvitelCustomers = (UgyvitelCustomers) jaxbUnmarshaller.unmarshal(reader);

    MediaType mediaType = MediaType.parse("application/xml");

    jaxbContext = JAXBContext.newInstance(UnasCustomers.class);
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    StringWriter sw = new StringWriter();
    jaxbMarshaller.marshal(mapUgyvitelCustomersToUnasCustomers(ugyvitelCustomers), sw);

    RequestBody body = RequestBody.create(mediaType, sw.toString());

    Request setCustomerRequest = new Request.Builder()
        .url(unasapiServiceUrl + UnasMServiceEndpoints.SET_CUSTOMERS.toString())
        .post(body)
        .addHeader("ApiKey", apiKey)
        .build();
    Response response = client.newCall(setCustomerRequest).execute();

    return response.body().string();
  }

  @Override
  public Object sendUgyvitelOrderToUnas(String apiKey, String Orders) throws IOException, JAXBException {
    
    JAXBContext jaxbContext = JAXBContext.newInstance(UgyvitelOrders.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    StringReader reader = new StringReader(Orders);

    UgyvitelOrders ugyvitelOrders = (UgyvitelOrders) jaxbUnmarshaller.unmarshal(reader);

    MediaType mediaType = MediaType.parse("application/xml");

    jaxbContext = JAXBContext.newInstance(UnasCustomers.class);
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    StringWriter sw = new StringWriter();
    jaxbMarshaller.marshal(mapUgyvitelOrdersToUnasOrders(ugyvitelOrders), sw);

    RequestBody body = RequestBody.create(mediaType, sw.toString());

    Request setOrderRequest = new Request.Builder()
        .url(unasapiServiceUrl + UnasMServiceEndpoints.SET_ORDERS.toString())
        .post(body)
        .addHeader("ApiKey", apiKey)
        .build();
    Response response = client.newCall(setOrderRequest).execute();

    return response.body().string();
  }

  @Override
  public Object sendUgyvitelStockToUnas(String apiKey, String Products) throws IOException, JAXBException {
    
    JAXBContext jaxbContext = JAXBContext.newInstance(UgyvitelProducts.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    StringReader reader = new StringReader(Products);

    UgyvitelProducts ugyvitelProducts = (UgyvitelProducts) jaxbUnmarshaller.unmarshal(reader);

    MediaType mediaType = MediaType.parse("application/xml");

    jaxbContext = JAXBContext.newInstance(UnasProducts.class);
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    StringWriter sw = new StringWriter();
    jaxbMarshaller.marshal(mapUgyvitelStocksToUnasStocks(ugyvitelProducts), sw);

    RequestBody body = RequestBody.create(mediaType, sw.toString());

    Request setStockRequest = new Request.Builder()
        .url(unasapiServiceUrl + UnasMServiceEndpoints.SET_STOCKS.toString())
        .post(body)
        .addHeader("ApiKey", apiKey)
        .build();
    Response response = client.newCall(setStockRequest).execute();

    return response.body().string();
  }

  @Override
  public Object sendUgyvitelCategoryToUnas(String apiKey, String Categories) throws IOException, JAXBException {
   
    JAXBContext jaxbContext = JAXBContext.newInstance(UgyvitelCategories.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    StringReader reader = new StringReader(Categories);

    UgyvitelCategories ugyvitelCategories = (UgyvitelCategories) jaxbUnmarshaller.unmarshal(reader);

    MediaType mediaType = MediaType.parse("application/xml");

    jaxbContext = JAXBContext.newInstance(UnasCategories.class);
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    StringWriter sw = new StringWriter();
    jaxbMarshaller.marshal(mapUgyvitelCategoriesToUnasCategories(ugyvitelCategories), sw);

    RequestBody body = RequestBody.create(mediaType, sw.toString());

    Request setCategoryRequest = new Request.Builder()
        .url(unasapiServiceUrl + UnasMServiceEndpoints.SET_CATEGORIES.toString())
        .post(body)
        .addHeader("ApiKey", apiKey)
        .build();
    Response response = client.newCall(setCategoryRequest).execute();

    return response.body().string();
  }

  private Object mapUgyvitelCategoriesToUnasCategories(UgyvitelCategories ugyvitelCategories) {

    UnasCategories unasCategories = new UnasCategories();

    for (UgyvitelCategory ugyvitelCategory : ugyvitelCategories.categories) {
      UnasCategory unasCategory = new UnasCategory();

      unasCategory.id = ugyvitelCategory.id;
      unasCategory.name = ugyvitelCategory.name;
      unasCategory.display.menu = ugyvitelCategory.visible == "1" ? "yes" : "no";
      unasCategory.display.page = ugyvitelCategory.visible == "1" ? "yes" : "no";
      unasCategory.parent.id = ugyvitelCategory.parentId;
      unasCategory.order = ugyvitelCategory.categoryOrder;

      unasCategories.category.add(unasCategory);
    }

    return unasCategories;
  }

  private Object mapUgyvitelStocksToUnasStocks(UgyvitelProducts ugyvitelProducts) {
    
    UnasProducts unasProducts = new UnasProducts();
    
    unasProducts.products = new ArrayList<>();

    for (UgyvitelProduct ugyvitelProduct : ugyvitelProducts.product) {
      UnasProduct unasProduct = new UnasProduct();

      com.ks.supersync.model.unas.product.stock.Stock stock = new com.ks.supersync.model.unas.product.stock.Stock();
      stock.price = ugyvitelProduct.lastPurchasePrice;

      for (Stock ugyvitelstock : ugyvitelProduct.stocks.stock) {
        stock.qty += ugyvitelstock.freeStock;
      }
      unasProduct.stocks.stock.add(stock);
      unasProducts.products.add(unasProduct);
    }
    return unasProducts;
  }


  private Object mapUgyvitelOrdersToUnasOrders(UgyvitelOrders ugyvitelOrders) {
    
    UnasOrders unasOrders = new UnasOrders();
    
    unasOrders.orders = new ArrayList<>();

    for (UgyvitelOrder ugyvitelOrder : ugyvitelOrders.order) {
      UnasOrder unasOrder = new UnasOrder();

      unasOrder.key = ugyvitelOrder.externalId;
      unasOrder.date = ugyvitelOrder.orderedDate;
      unasOrder.dateMod = "";
      unasOrder.customer.id = ugyvitelOrder.customerWebshopId;
      unasOrder.customer.email = ugyvitelOrder.Email;
      unasOrder.customer.userName = "";
      unasOrder.customer.contact.name = ugyvitelOrder.customerName;
      unasOrder.customer.contact.phone = ugyvitelOrder.Phone;
      unasOrder.customer.contact.mobile = ugyvitelOrder.Phone;
      unasOrder.customer.contact.lang = "";
      unasOrder.addresses.invoice.name = ugyvitelOrder.customerName;
      unasOrder.addresses.invoice.ZIP = ugyvitelOrder.orderAddressZip;
      unasOrder.addresses.invoice.city = ugyvitelOrder.orderAddressCity;
      unasOrder.addresses.invoice.country = ugyvitelOrder.orderAddressCountry;
      unasOrder.addresses.invoice.countryCode = "";
      unasOrder.addresses.invoice.county = "";
      unasOrder.addresses.invoice.euTaxNumber = "";
      unasOrder.addresses.invoice.street = "";
      unasOrder.addresses.invoice.streetName = ugyvitelOrder.orderAddressStreet;
      unasOrder.addresses.invoice.streetType = ugyvitelOrder.orderAddressPublicDomain;
      unasOrder.addresses.invoice.streetNumber = ugyvitelOrder.orderAddressNumber;
      unasOrder.addresses.invoice.taxNumber = ugyvitelOrder.taxNumber;
      unasOrder.addresses.shipping.name = ugyvitelOrder.customerName != null ? ugyvitelOrder.customerName : "";
      unasOrder.addresses.shipping.ZIP = ugyvitelOrder.deliveryAddressZip != null ? ugyvitelOrder.deliveryAddressZip : "" ;
      unasOrder.addresses.shipping.city = ugyvitelOrder.deliveryAddressCity != null ? ugyvitelOrder.deliveryAddressCity :"" ;
      unasOrder.addresses.shipping.country = ugyvitelOrder.deliveryAddressCountry != null ? ugyvitelOrder.deliveryAddressCountry : "";
      unasOrder.addresses.shipping.countryCode = "";
      unasOrder.addresses.shipping.county = "";
      unasOrder.addresses.shipping.street = "";
      unasOrder.addresses.shipping.streetName = ugyvitelOrder.deliveryAddressStreet != null ? ugyvitelOrder.deliveryAddressStreet : "";
      unasOrder.addresses.shipping.streetType = ugyvitelOrder.deliveryAddressPublicDomain != null ? ugyvitelOrder.deliveryAddressPublicDomain : "";
      unasOrder.addresses.shipping.streetNumber = ugyvitelOrder.deliveryAddressNumber != null ? ugyvitelOrder.deliveryAddressNumber : "";
      // group and params missing 
      unasOrder.currency = ugyvitelOrder.currency;
      unasOrder.payment.name = ugyvitelOrder.paymentmethod;

      if(ugyvitelOrder.bottomComment != null){
        Comment comment = new Comment();
        comment.text = ugyvitelOrder.bottomComment;
        unasOrder.comments.comments.add(comment);
      }

      if(ugyvitelOrder.topComment!= null){
        Comment comment = new Comment();
        comment.text = ugyvitelOrder.topComment;
        unasOrder.comments.comments.add(comment);
      }

      for (Detail detail : ugyvitelOrder.details.detail){
        Item item = new Item();
        item.sku = detail.productCode;
        item.name = detail.productName;
        item.unit = detail.quantityUnit;
        item.quantity = detail.quantity;
        item.priceNet = detail.unitPrice;
        item.priceGross = detail.grossValue / detail.quantity;
        item.vat = detail.vatCode;
        unasOrder.items.item.add(item);
      }

      unasOrders.orders.add(unasOrder);
    }
    return unasOrders;
  }


  private UnasCustomers mapUgyvitelCustomersToUnasCustomers(UgyvitelCustomers ugyvitelCustomers) {
    UnasCustomers unasCustomers = new UnasCustomers();
    
    unasCustomers.customer = new ArrayList<>();

    for (UgyvitelCustomer ugyvitelCustomer : ugyvitelCustomers.customer) {

      UnasCustomer unasCustomer = new UnasCustomer();

      unasCustomer.id = ugyvitelCustomer.customerId;
      unasCustomer.email = ugyvitelCustomer.email;
      unasCustomer.contact.name = ugyvitelCustomer.customerName;
      unasCustomer.contact.phone = ugyvitelCustomer.phone;
      unasCustomer.contact.mobile = ugyvitelCustomer.phone != null ? ugyvitelCustomer.phone : "";
      unasCustomer.contact.lang = ugyvitelCustomer.countryCode;
      unasCustomer.addresses.invoice.ZIP = ugyvitelCustomer.centralZip;
      unasCustomer.addresses.invoice.city = ugyvitelCustomer.centralCity;
      unasCustomer.addresses.invoice.country = ugyvitelCustomer.centralCountry;
      unasCustomer.addresses.invoice.countryCode = ugyvitelCustomer.countryCode.toLowerCase();
      unasCustomer.addresses.invoice.name = ugyvitelCustomer.centralAddressName != null ? ugyvitelCustomer.centralAddressName : ugyvitelCustomer.customerName;
      if(ugyvitelCustomer.centralPublicDomain == null){
        unasCustomer.addresses.invoice.streetName = ugyvitelCustomer.centralStreet;
      } else { 
        unasCustomer.addresses.invoice.streetName = ugyvitelCustomer.centralStreet;
        unasCustomer.addresses.invoice.streetType = ugyvitelCustomer.centralPublicDomain;
        unasCustomer.addresses.invoice.streetNumber = ugyvitelCustomer.centralNumber;
      }
      unasCustomer.addresses.shipping.ZIP = ugyvitelCustomer.deliveryZip;
      unasCustomer.addresses.shipping.city = ugyvitelCustomer.deliveryCity;
      unasCustomer.addresses.shipping.country = ugyvitelCustomer.deliveryCountry;
      unasCustomer.addresses.shipping.country = ugyvitelCustomer.deliveryCountryCode != null ? ugyvitelCustomer.deliveryCountryCode.toLowerCase() : "";
      unasCustomer.addresses.shipping.name = ugyvitelCustomer.deliveryAddressName != null ? ugyvitelCustomer.deliveryAddressName : ugyvitelCustomer.customerName;
      if(ugyvitelCustomer.deliveryPublicDomain == null){
        unasCustomer.addresses.shipping.streetName = ugyvitelCustomer.deliveryStreet;
      } else {
        unasCustomer.addresses.shipping.streetName = ugyvitelCustomer.deliveryStreet;
        unasCustomer.addresses.shipping.streetType = ugyvitelCustomer.deliveryPublicDomain;
        unasCustomer.addresses.shipping.streetNumber = ugyvitelCustomer.deliveryNumber;
      }


      System.out.println(unasCustomer.contact.name);
      unasCustomers.customer.add(unasCustomer);
    }
    return unasCustomers;
  }


  private UgyvitelProducts mapUnasProductsToUgyvitelProducts(UnasProducts unasProducts){

    UgyvitelProducts ugyvitelProducts = new UgyvitelProducts();
    ugyvitelProducts.product = new ArrayList<>();

    for (UnasProduct unasProduct : unasProducts.products) {
      UgyvitelProduct ugyvitelProduct = new UgyvitelProduct();
      ugyvitelProduct.productId = 0;
      ugyvitelProduct.webshopId = unasProduct.id;
      ugyvitelProduct.productCode = unasProduct.sku;
      ugyvitelProduct.productName.pName = unasProduct.name;
      ugyvitelProduct.itemNumber = "";
      if(unasProduct.description != null){
        ugyvitelProduct.comment.comment.add(unasProduct.description.shortDesc); //felülvizsgálni
      }
      ugyvitelProduct.barCode = "";
      ugyvitelProduct.lastPurchasePrice = "";
      ugyvitelProduct.active = unasProduct.state.equals("live") ? 1 : 0;
      ugyvitelProduct.vatCode = unasProduct.Prices != null ? unasProduct.Prices.vat : "";
      ugyvitelProduct.quantityUnit.TranslatedQuantityUnit.add(unasProduct.unit);
      ugyvitelProduct.service = unasProduct.stocks != null && unasProduct.stocks.stock != null && unasProduct.stocks.status != null ? unasProduct.stocks.status.active : 0 ;
      if(unasProduct.Prices != null){
        for (Price price : unasProduct.Prices.prices) {
          if(price.type.equals("normal")){
            ugyvitelProduct.unitPrice = price.net != null ? price.net : "";
          } else {
            PriceRule priceRule =  new PriceRule();
            priceRule.priceRuleId = "";
            priceRule.validFrom = price.saleStart;
            priceRule.validTo = price.saleEnd;
            priceRule.priceRuleName = price.type;
            priceRule.currency = "";
            priceRule.price = price.net;
            ugyvitelProduct.priceRules.priceRule.add(priceRule);
          }
        }
      } 
      else {
        ugyvitelProduct.unitPrice = "";
      }
      ugyvitelProducts.product.add(ugyvitelProduct);
    }
    return ugyvitelProducts;
  }

  private UgyvitelCustomers mapUnasCustomersToUgyvitelCustomers(UnasCustomers unasCustomers){

    UgyvitelCustomers ugyvitelCustomers = new UgyvitelCustomers();
    ugyvitelCustomers.customer = new ArrayList<>();

    for(UnasCustomer unasCustomer : unasCustomers.customer){
        UgyvitelCustomer ugyvitelCustomer = new UgyvitelCustomer();
        ugyvitelCustomer.webshopId = unasCustomer.id;
        ugyvitelCustomer.customerCode = unasCustomer.id;
        ugyvitelCustomer.customerName = unasCustomer.contact.name;
        ugyvitelCustomer.email = unasCustomer.email;
        ugyvitelCustomer.phone = unasCustomer.contact != null ? unasCustomer.contact.phone : "";
        ugyvitelCustomer.contactName = unasCustomer.contact.name;
        ugyvitelCustomer.centralAddressName = unasCustomer.addresses.invoice.name;
        ugyvitelCustomer.centralCountry = unasCustomer.addresses.invoice.country;
        ugyvitelCustomer.centralZip = unasCustomer.addresses.invoice.ZIP;
        ugyvitelCustomer.centralCity = unasCustomer.addresses.invoice.city;
        ugyvitelCustomer.centralStreet = unasCustomer.addresses.invoice.street;
        ugyvitelCustomer.centralPublicDomain = unasCustomer.addresses.invoice.streetType;
        ugyvitelCustomer.centralStaircase = "";
        ugyvitelCustomer.centralDoor = "";
        ugyvitelCustomer.centralFloor = "";
        ugyvitelCustomer.centralNumber =  unasCustomer.addresses.invoice.streetNumber;
        ugyvitelCustomer.centralBuilding = "";
        ugyvitelCustomer.deliveryAddressName = unasCustomer.addresses.shipping.name;
        ugyvitelCustomer.deliveryCountry = unasCustomer.addresses.shipping.country;
        ugyvitelCustomer.deliveryCity = unasCustomer.addresses.shipping.city;
        ugyvitelCustomer.deliveryStreet = unasCustomer.addresses.shipping.street;
        ugyvitelCustomer.deliveryZip = unasCustomer.addresses.shipping.ZIP;
        ugyvitelCustomer.deliveryPublicDomain = unasCustomer.addresses.shipping.streetType;
        ugyvitelCustomer.deliveryStaircase = "";
        ugyvitelCustomer.deliveryDoor = "";
        ugyvitelCustomer.deliveryFloor= "";
        ugyvitelCustomer.deliveryNumber = unasCustomer.addresses.shipping.streetNumber;
        ugyvitelCustomer.deliveryBuilding = "";

       /*  if(ugyvitelCustomer.otherAddresses != null && ugyvitelCustomer.otherAddresses.otherAddress != null){
          for (OtherAddress ugyvitelOtherAddress : ugyvitelCustomer.otherAddresses.otherAddress) {
            Other unasOtherAddress = new Other();
  
            unasOtherAddress.ZIP = ugyvitelOtherAddress.otherZip;
            unasOtherAddress.city = ugyvitelOtherAddress.otherCity;
            unasOtherAddress.country = ugyvitelOtherAddress.otherCountry;
            unasOtherAddress.name = ugyvitelOtherAddress.otherAddressName;
            unasOtherAddress.streetName = ugyvitelOtherAddress.otherStreet;
            unasOtherAddress.streetType = ugyvitelOtherAddress.otherPublicDomain;
            unasOtherAddress.streetNumber = ugyvitelOtherAddress.otherNumber;
  
            unasCustomer.addresses.others.add(unasOtherAddress);
          }
        }
  
        if(ugyvitelCustomer.categories != null && ugyvitelCustomer.categories.category !=null){
          for (com.ks.supersync.model.ugyvitel.customer.Category ugyvitelCategory : ugyvitelCustomer.categories.category) {
            if(ugyvitelCategory.isBaseCategory.equals("true")){
              unasCustomer.group.id = ugyvitelCategory.categoryId;
              unasCustomer.group.name = ugyvitelCategory.categoryValue;
            }
          }
        } */

        ugyvitelCustomers.customer.add(ugyvitelCustomer);
    }

    return ugyvitelCustomers;
  }

  private UgyvitelOrders mapUnasOrdersToUgyvitelOrders(UnasOrders unasOrders){

    UgyvitelOrders ugyvitelOrders = new UgyvitelOrders();
    ugyvitelOrders.order = new ArrayList<>();
    for (UnasOrder unasOrder : unasOrders.orders) {
      UgyvitelOrder ugyvitelOrder = new UgyvitelOrder();
      ugyvitelOrder.webshopId = unasOrder.key;
      ugyvitelOrder.currency = unasOrder.currency;
      ugyvitelOrder.customerWebshopId = "";
      ugyvitelOrder.customerCode = unasOrder.customer != null ? unasOrder.customer.id : "";
      ugyvitelOrder.Phone = unasOrder.customer != null ? unasOrder.customer.contact.phone : "";
      ugyvitelOrder.Email = unasOrder.customer != null ? unasOrder.customer.email : "";
      ugyvitelOrder.customerName = "";
      ugyvitelOrder.customerContact = unasOrder.customer != null ? unasOrder.customer.contact.name : "";
      ugyvitelOrder.invoicingZip = unasOrder.customer != null && unasOrder.customer.addresses != null && unasOrder.customer.addresses.invoice != null ? unasOrder.customer.addresses.invoice.ZIP : "";
      ugyvitelOrder.invoicingCity = unasOrder.customer != null && unasOrder.customer.addresses != null && unasOrder.customer.addresses.invoice != null ? unasOrder.customer.addresses.invoice.city : "";
      ugyvitelOrder.invoicingStreet = unasOrder.customer != null && unasOrder.customer.addresses != null && unasOrder.customer.addresses.invoice != null ? unasOrder.customer.addresses.invoice.street : "";
      ugyvitelOrder.invoicingStaircase = "";
      ugyvitelOrder.invoicingDoor = "";
      ugyvitelOrder.invoicingFloor = "";
      ugyvitelOrder.invoicingBuilding = "";
      ugyvitelOrder.invoicingNumber = unasOrder.customer != null && unasOrder.customer.addresses != null && unasOrder.customer.addresses.invoice != null ? unasOrder.customer.addresses.invoice.streetNumber : "";
      ugyvitelOrder.invoicingPublicDomain = unasOrder.customer != null && unasOrder.customer.addresses != null && unasOrder.customer.addresses.invoice != null ? unasOrder.customer.addresses.invoice.streetType : "";
      ugyvitelOrder.invoicingCountry = unasOrder.customer != null && unasOrder.customer.addresses != null && unasOrder.customer.addresses.invoice != null ? unasOrder.customer.addresses.invoice.country : "";
      ugyvitelOrder.deliveryName = unasOrder.customer != null && unasOrder.customer.addresses != null && unasOrder.customer.addresses.shipping != null ? unasOrder.customer.addresses.shipping.name : "";
      ugyvitelOrder.deliveryZip = ugyvitelOrder.deliveryName = unasOrder.customer != null && unasOrder.customer.addresses != null && unasOrder.customer.addresses.shipping != null ? unasOrder.customer.addresses.shipping.ZIP : "";
      ugyvitelOrder.deliveryCity = ugyvitelOrder.deliveryName = unasOrder.customer != null && unasOrder.customer.addresses != null && unasOrder.customer.addresses.shipping != null ? unasOrder.customer.addresses.shipping.city : "";
      ugyvitelOrder.deliveryStreet = ugyvitelOrder.deliveryName = unasOrder.customer != null && unasOrder.customer.addresses != null && unasOrder.customer.addresses.shipping != null ? unasOrder.customer.addresses.shipping.street : "";
      ugyvitelOrder.deliveryStaircase = "";
      ugyvitelOrder.deliveryDoor = "";
      ugyvitelOrder.deliveryFloor = "";
      ugyvitelOrder.deliveryBuilding = "";
      ugyvitelOrder.deliveryNumber = ugyvitelOrder.deliveryName = unasOrder.customer != null && unasOrder.customer.addresses != null && unasOrder.customer.addresses.shipping != null ? unasOrder.customer.addresses.shipping.streetNumber : "";
      ugyvitelOrder.deliveryPublicDomain = ugyvitelOrder.deliveryName = unasOrder.customer != null && unasOrder.customer.addresses != null && unasOrder.customer.addresses.shipping != null ? unasOrder.customer.addresses.shipping.streetType : "";
      ugyvitelOrder.deliveryCountry = ugyvitelOrder.deliveryName = unasOrder.customer != null && unasOrder.customer.addresses != null && unasOrder.customer.addresses.shipping != null ? unasOrder.customer.addresses.shipping.country : "";
      ugyvitelOrder.taxNumber = ugyvitelOrder.deliveryName = unasOrder.customer != null && unasOrder.customer.addresses != null && unasOrder.customer.addresses.shipping != null ? unasOrder.customer.addresses.invoice.taxNumber : "";
      ugyvitelOrder.sequencePrefix = "";
      ugyvitelOrder.paymentDelayDays = "";
      ugyvitelOrder.paymentmethod = unasOrder.payment != null ? unasOrder.payment.name : "";
      ugyvitelOrder.deliveryDate = "";
      ugyvitelOrder.orderDate = "";
      ugyvitelOrder.transportMode = unasOrder.shipping != null ? unasOrder.shipping.name : "";
      ugyvitelOrder.topComment = "";
      ugyvitelOrder.bottomComment = "";
      if(unasOrder.items.item != null){
        ugyvitelOrder.details = new Details();
        ugyvitelOrder.details.detail = new ArrayList<>();
        for (Item item : unasOrder.items.item) {
          Detail detail = new Detail();
          detail.webshopId = "";
          detail.productCode = item.sku;
          detail.productName = item.name;
          detail.quantity = item.quantity;
          detail.quantityUnit = item.unit;
          detail.unitPrice = item.priceNet;
          detail.stock = "";
          detail.productComment = ""; //ellenőrizni
          detail.vatCode = item.vat;
          ugyvitelOrder.details.detail.add(detail);
        }
      }
      ugyvitelOrders.order.add(ugyvitelOrder);
    }

    return ugyvitelOrders;
  }

  private UnasProducts mapUgyvitelProductsToUnasProducts(UgyvitelProducts ugyvitelProducts){
    UnasProducts unasProducts = new UnasProducts();

    unasProducts.products = new ArrayList<>();

    for (UgyvitelProduct ugyvitelProduct : ugyvitelProducts.product) {
      UnasProduct unasProduct = new UnasProduct();
      unasProduct.Prices = new Prices();
      unasProduct.Prices.prices = new ArrayList<>();
      unasProduct.description = new Description();
      Price unitPrice = new Price();

      unasProduct.sku = ugyvitelProduct.productCode;
      unasProduct.name = ugyvitelProduct.productName.pName;
      unasProduct.unit = ugyvitelProduct.quantityUnit.TranslatedQuantityUnit.get(0);
      unasProduct.description.shortDesc = ugyvitelProduct.comment.comment.get(0);
      unasProduct.description.longDesc = "";
      unitPrice.type = "normal";
      unitPrice.net = ugyvitelProduct.unitPrice;
      //unitPrice.gross = "0";
      unasProduct.Prices.prices.add(unitPrice);
      Price unasSalePrice = new Price();
      unasSalePrice.type = "sale";
      unasSalePrice.net = ugyvitelProduct.discountPrice;
      unasProduct.Prices.prices.add(unasSalePrice);
      if(ugyvitelProduct.priceRules != null){          
        if(ugyvitelProduct.priceRules.priceRule != null){
          for (PriceRule pRule : ugyvitelProduct.priceRules.priceRule) {
            if(pRule.priceRuleName.equals("Egységár")){
              continue;
            } else {
              if(pRule.price.equals(ugyvitelProduct.discountPrice)){
                for (Price unasPrices : unasProduct.Prices.prices) {
                  if(unasPrices.type.equals("sale")){
                    unasPrices.saleStart = pRule.validFrom;
                    unasPrices.saleEnd = pRule.validTo;
                  }
                }
              } else {
                Price unasPrice = new Price();
                unasPrice.type =  "special"; //felülvizsgálni
                unasPrice.net = pRule.price; //felülvizsgálni
                //unasPrice.gross = "345"; //kötelező mező
                unasPrice.start = pRule.validFrom; //felülvizsgálni
                unasPrice.end = pRule.validTo; //felülvizsgálni
                unasProduct.Prices.prices.add(unasPrice);
              }
            }
          }
        }
      } else {
        unasProduct.Prices.prices.add(unitPrice);
      }
      for (com.ks.supersync.model.ugyvitel.product.Category ugyvitelCatergoy : ugyvitelProduct.categories.category) {
        Category unasCategory = new Category();
        unasCategory.id = ugyvitelCatergoy.categoryId;
        unasProduct.categories.category.add(unasCategory);
      }
      unasProducts.products.add(unasProduct);
    }
    return unasProducts;
  }
}