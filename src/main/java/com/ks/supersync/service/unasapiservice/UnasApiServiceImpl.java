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
import com.ks.supersync.model.unas.customer.UnasCustomer;
import com.ks.supersync.model.unas.customer.UnasCustomers;
import com.ks.supersync.model.unas.order.Item;
import com.ks.supersync.model.unas.order.UnasOrder;
import com.ks.supersync.model.unas.order.UnasOrders;
import com.ks.supersync.model.unas.product.Categories;
import com.ks.supersync.model.unas.product.Category;
import com.ks.supersync.model.unas.product.Description;
import com.ks.supersync.model.unas.product.Price;
import com.ks.supersync.model.unas.product.Prices;
import com.ks.supersync.model.unas.product.UnasProduct;
import com.ks.supersync.model.unas.product.UnasProducts;
import com.ks.supersync.model.unas.product.stock.Stocks;

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

      //todo map
      
      unasOrders.orders.add(unasOrder);
    }
    return unasOrders;
  }


  private UnasCustomers mapUgyvitelCustomersToUnasCustomers(UgyvitelCustomers ugyvitelCustomers) {
    UnasCustomers unasCustomers = new UnasCustomers();
    
    unasCustomers.customer = new ArrayList<>();

    for (UgyvitelCustomer ugyvitelCustomer : ugyvitelCustomers.customer) {
      UnasCustomer unasCustomer = new UnasCustomer();

      unasCustomer.email = ugyvitelCustomer.email;
      unasCustomer.contact.name = ugyvitelCustomer.contactName;
      unasCustomer.contact.phone = ugyvitelCustomer.phone;
      unasCustomer.contact.mobile = ugyvitelCustomer.phone != null ? ugyvitelCustomer.phone : "";
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
      ugyvitelProduct.lastPurchasePrice = 0;
      ugyvitelProduct.active = unasProduct.state.equals("live") ? 1 : 0;
      ugyvitelProduct.vatCode = unasProduct.Prices != null ? unasProduct.Prices.vat : "";
      ugyvitelProduct.quantityUnit.TranslatedQuantityUnit.add(unasProduct.unit);
      ugyvitelProduct.service = unasProduct.stocks != null ? unasProduct.stocks.status.active : 0 ;
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
      if(unasOrder.Items != null){
        ugyvitelOrder.details = new Details();
        ugyvitelOrder.details.detail = new ArrayList<>();
        for (Item item : unasOrder.Items.item) {
          Detail detail = new Detail();
          detail.webshopId = "";
          detail.productCode = item.sku;
          detail.productName = item.name;
          detail.quantity = item.quantity;
          detail.quantityUnit = item.unit;
          detail.unitPrice = "";
          detail.stock = "";
          detail.productComment = "";
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
      unasProduct.unit = ugyvitelProduct.quantityUnit.TranslatedQuantityUnit.get(0); //felülvizsgálni
      /* unasProduct.minimumQty = 0;
      unasProduct.maximumQty = 0; */
      unasProduct.description.shortDesc = ugyvitelProduct.comment.comment.get(0); //felülvizsgálni
      unasProduct.description.longDesc = "";
      unitPrice.type = "normal";
      unitPrice.net = ugyvitelProduct.unitPrice;
      unitPrice.gross = "0"; //felülvizsgálni
      unasProduct.Prices.prices.add(unitPrice);
      if(ugyvitelProduct.priceRules != null){          
        if(ugyvitelProduct.priceRules.priceRule != null){
          for (PriceRule pRule : ugyvitelProduct.priceRules.priceRule) {
            Price uPrice = new Price();
            uPrice.type = pRule.priceRuleName; //felülvizsgálni
            uPrice.net = pRule.price; //felülvizsgálni
            uPrice.gross = "345"; //kötelező mező
            uPrice.start = pRule.validFrom; //felülvizsgálni
            uPrice.end = pRule.validTo; //felülvizsgálni
            unasProduct.Prices.prices.add(uPrice);
          }
        }
      } else {
        unasProduct.Prices.prices.add(unitPrice);
      }
      unasProduct.categories = new Categories();
      unasProduct.categories.category = new ArrayList<>();
      Category category = new Category();
      category.id = 1;
      category.name = "Teszt";
      category.type = "base";
      unasProduct.categories.category.add(category);
      unasProducts.products.add(unasProduct);
    }

    return unasProducts;
  }
}