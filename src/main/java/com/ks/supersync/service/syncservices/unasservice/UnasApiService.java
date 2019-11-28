package com.ks.supersync.service.syncservices.unasservice;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.ks.supersync.model.supersync.I18n;
import com.ks.supersync.model.ugyvitel.category.UgyvitelCategories;
import com.ks.supersync.model.ugyvitel.category.UgyvitelCategory;
import com.ks.supersync.model.ugyvitel.customer.UgyvitelCustomer;
import com.ks.supersync.model.ugyvitel.customer.UgyvitelCustomers;
import com.ks.supersync.model.ugyvitel.order.UgyvitelOrder;
import com.ks.supersync.model.ugyvitel.order.UgyvitelOrders;
import com.ks.supersync.model.ugyvitel.order.detail.Detail;
import com.ks.supersync.model.ugyvitel.order.detail.Details;
import com.ks.supersync.model.ugyvitel.product.PriceRule;
import com.ks.supersync.model.ugyvitel.product.Stock;
import com.ks.supersync.model.ugyvitel.product.TranslatedComment;
import com.ks.supersync.model.ugyvitel.product.TranslatedName;
import com.ks.supersync.model.ugyvitel.product.TranslatedQuantityUnit;
import com.ks.supersync.model.ugyvitel.product.UgyvitelProduct;
import com.ks.supersync.model.ugyvitel.product.UgyvitelProducts;
import com.ks.supersync.model.unas.category.UnasCategories;
import com.ks.supersync.model.unas.category.UnasCategory;
import com.ks.supersync.model.unas.customer.UnasCustomer;
import com.ks.supersync.model.unas.customer.UnasCustomers;
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
import com.ks.supersync.repository.I18nRepository;
import com.ks.supersync.service.syncservices.SyncService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import okhttp3.*;

@Service
public class UnasApiService implements SyncService {

  OkHttpClient client = new OkHttpClient();

  @Value("${unasapi.serviceurl}")
  private String unasapiServiceUrl;

  private final I18nRepository i18nRepository;

  private JAXBContext jaxbContext;

  private Unmarshaller jaxbUnmarshaller;
  private Marshaller jaxbMarshaller;

  private StringReader reader;
  private StringWriter writer;

  public UnasApiService(final I18nRepository i18nRepository) {
    this.i18nRepository = i18nRepository;
  }

  private Object createObjectFromXMLString(final String XMLString, final Class c) throws JAXBException {

    this.jaxbContext = JAXBContext.newInstance(c);
    this.jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    this.reader = new StringReader(XMLString);
    return jaxbUnmarshaller.unmarshal(reader);

  }

  private String createXMLStringFromObject(final Class c, final Object mappedObject) throws JAXBException {

    this.jaxbContext = JAXBContext.newInstance(c);
    this.jaxbMarshaller = jaxbContext.createMarshaller();
    this.jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    this.writer = new StringWriter();
    this.jaxbMarshaller.marshal(mappedObject, this.writer);

    return this.writer.toString();
  }

  @Override
  public UgyvitelProducts getProductsToUgyvitel(final String apiKey) throws IOException, JAXBException {

    UgyvitelProducts uProducts = new UgyvitelProducts();

    client.newBuilder().connectTimeout(15, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS).readTimeout(15,
        TimeUnit.SECONDS);

    final Request request = new Request.Builder()
        .url(unasapiServiceUrl + UnasMServiceEndpoints.GET_PRODUCTS.toString() + "?debug=true").get()
        .addHeader("ApiKey", apiKey).build();
    final Response response = client.newCall(request).execute();

    final UnasProducts unasProducts = (UnasProducts) createObjectFromXMLString(response.body().string(), UnasProducts.class);

    uProducts = mapUnasProductsToUgyvitelProducts(unasProducts, apiKey);

    return uProducts;
  }

  @Override
  public UgyvitelCustomers getCustomersToUgyvitel(final String apiKey) throws IOException, JAXBException {

    UgyvitelCustomers uCustomers = new UgyvitelCustomers();

    final Request request = new Request.Builder()
        .url(unasapiServiceUrl + UnasMServiceEndpoints.GET_CUSTOMERS.toString()).get().addHeader("ApiKey", apiKey)
        .build();
    final Response response = client.newCall(request).execute();

    final UnasCustomers unasCustomers = (UnasCustomers) createObjectFromXMLString(response.body().string(), UnasCustomers.class);

    uCustomers = mapUnasCustomersToUgyvitelCustomers(unasCustomers);

    return uCustomers;
  }

  @Override
  public UgyvitelOrders getOrdersToUgyvitel(final String apiKey) throws IOException, JAXBException {

    UgyvitelOrders uOrders = new UgyvitelOrders();

    final Request request = new Request.Builder().url(unasapiServiceUrl + UnasMServiceEndpoints.GET_ORDERS.toString())
        .get().addHeader("ApiKey", apiKey).build();
    final Response response = client.newCall(request).execute();

    final UnasOrders unasOrders = (UnasOrders) createObjectFromXMLString(response.body().string(), UnasOrders.class);

    uOrders = mapUnasOrdersToUgyvitelOrders(unasOrders);

    return uOrders;
  }

  @Override
  public Object sendUgyvitelProducts(final String apiKey, final String Products)
      throws IOException, JAXBException {

    final UgyvitelProducts ugyvitelProducts = (UgyvitelProducts) createObjectFromXMLString(Products, UgyvitelProducts.class);

    final MediaType mediaType = MediaType.parse("application/xml");

    final UnasProducts mappedUnasProducts = mapUgyvitelProductsToUnasProducts(ugyvitelProducts, apiKey);

    final RequestBody body = RequestBody.create(mediaType, createXMLStringFromObject(UnasProducts.class, mappedUnasProducts));

    final Request setProductRequest = new Request.Builder()
        .url(unasapiServiceUrl + UnasMServiceEndpoints.SET_PRODUCTS.toString()).post(body).addHeader("ApiKey", apiKey)
        .build();
    final Response response = client.newCall(setProductRequest).execute();

    return response.body().string();
  }

  @Override
  public Object sendUgyvitelCustomers(final String apiKey, final String Customers)
      throws IOException, JAXBException {

    final UgyvitelCustomers ugyvitelCustomers = (UgyvitelCustomers) createObjectFromXMLString(Customers, UgyvitelCustomers.class);

    final UgyvitelCustomers validatedUgyvitelCustomers = new UgyvitelCustomers();
    validatedUgyvitelCustomers.customer = new ArrayList<>();
    final UgyvitelCustomers invalidUgyvitelCustomers = new UgyvitelCustomers();
    invalidUgyvitelCustomers.customer = new ArrayList<>();

    ValidateUgyvitelCustomersToUnas(ugyvitelCustomers, validatedUgyvitelCustomers, invalidUgyvitelCustomers);
    
    if(validatedUgyvitelCustomers.customer.size() == 0){
      return "No valid customer found for UNAS";
    }

    final MediaType mediaType = MediaType.parse("application/xml");

    final UnasCustomers mappedUnasCustomers = mapUgyvitelCustomersToUnasCustomers(validatedUgyvitelCustomers);
    
    final RequestBody body = RequestBody.create(mediaType, createXMLStringFromObject(UnasCustomers.class, mappedUnasCustomers));

    final Request setCustomerRequest = new Request.Builder()
        .url(unasapiServiceUrl + UnasMServiceEndpoints.SET_CUSTOMERS.toString()).post(body).addHeader("ApiKey", apiKey)
        .build();
    final Response responseFromUnas = client.newCall(setCustomerRequest).execute();

    final UnasCustomers responseToUgyvitel = (UnasCustomers) createObjectFromXMLString(responseFromUnas.body().string(), UnasCustomers.class);

    for (final UgyvitelCustomer ugyvCustomer : invalidUgyvitelCustomers.customer) {
      final UnasCustomer invalidCustomer = new UnasCustomer();
      invalidCustomer.email = ugyvCustomer.email;
      invalidCustomer.status = "error";
      responseToUgyvitel.customer.add(invalidCustomer);
    }
    return responseToUgyvitel;
  }

  @Override
  public Object sendUgyvitelOrders(final String apiKey, final String Orders) throws IOException, JAXBException {

    final UgyvitelOrders ugyvitelOrders = (UgyvitelOrders) createObjectFromXMLString(Orders, UgyvitelOrders.class);

    final MediaType mediaType = MediaType.parse("application/xml");

    final UnasOrders mappedUnasOrders = mapUgyvitelOrdersToUnasOrders(ugyvitelOrders);

    final RequestBody body = RequestBody.create(mediaType, createXMLStringFromObject(UnasOrders.class, mappedUnasOrders));

    final Request setOrderRequest = new Request.Builder()
        .url(unasapiServiceUrl + UnasMServiceEndpoints.SET_ORDERS.toString()).post(body).addHeader("ApiKey", apiKey)
        .build();
    final Response response = client.newCall(setOrderRequest).execute();

    return response.body().string();
  }

  @Override
  public Object sendUgyvitelStocks(final String apiKey, final String Products) throws IOException, JAXBException {

    final UgyvitelProducts ugyvitelProducts = (UgyvitelProducts) createObjectFromXMLString(Products, UgyvitelProducts.class);

    final MediaType mediaType = MediaType.parse("application/xml");

    final UnasProducts mappedUnasStocks = mapUgyvitelStocksToUnasStocks(ugyvitelProducts);

    final RequestBody body = RequestBody.create(mediaType, createXMLStringFromObject(UnasProducts.class, mappedUnasStocks));

    final Request setStockRequest = new Request.Builder()
        .url(unasapiServiceUrl + UnasMServiceEndpoints.SET_STOCKS.toString()).post(body).addHeader("ApiKey", apiKey)
        .build();
    final Response response = client.newCall(setStockRequest).execute();

    return response.body().string();
  }
  
  @Override
  public Object sendUgyvitelProductCategories(final String apiKey, final String Categories)
      throws IOException, JAXBException {

    final UgyvitelCategories ugyvitelCategories = (UgyvitelCategories) createObjectFromXMLString(Categories, UgyvitelCategories.class);

    final MediaType mediaType = MediaType.parse("application/xml");

    final UnasCategories mappedUnasCategories = mapUgyvitelCategoriesToUnasCategories(ugyvitelCategories);

    final RequestBody body = RequestBody.create(mediaType, createXMLStringFromObject(UnasCategories.class, mappedUnasCategories));

    final Request setCategoryRequest = new Request.Builder()
        .url(unasapiServiceUrl + UnasMServiceEndpoints.SET_CATEGORIES.toString()).post(body).addHeader("ApiKey", apiKey)
        .build();
    final Response response = client.newCall(setCategoryRequest).execute();

    return response.body().string();
  }

  private UnasCategories mapUgyvitelCategoriesToUnasCategories(final UgyvitelCategories ugyvitelCategories) {

    final UnasCategories unasCategories = new UnasCategories();

    for (final UgyvitelCategory ugyvitelCategory : ugyvitelCategories.categories) {
      final UnasCategory unasCategory = new UnasCategory();

      unasCategory.id = ugyvitelCategory.id;
      unasCategory.name = ugyvitelCategory.name;
     /*  unasCategory.display.menu = ugyvitelCategory.visible.equals("1") ? "yes" : "no";
      unasCategory.display.page = ugyvitelCategory.visible.equals("1") ? "yes" : "no"; */
      unasCategory.parent.tree = ugyvitelCategory.tree;
      unasCategory.order = ugyvitelCategory.categoryOrder;

      unasCategories.category.add(unasCategory);
    }

    return unasCategories;
  }

  private UnasProducts mapUgyvitelStocksToUnasStocks(final UgyvitelProducts ugyvitelProducts) {

    final UnasProducts unasProducts = new UnasProducts();

    unasProducts.products = new ArrayList<>();

    for (final UgyvitelProduct ugyvitelProduct : ugyvitelProducts.product) {
      final UnasProduct unasProduct = new UnasProduct();

      final com.ks.supersync.model.unas.product.stock.Stock stock = new com.ks.supersync.model.unas.product.stock.Stock();
      stock.price = ugyvitelProduct.lastPurchasePrice;

      for (final Stock ugyvitelstock : ugyvitelProduct.stocks.stock) {
        stock.qty += ugyvitelstock.freeStock;
      }
      unasProduct.stocks.stock.add(stock);
      unasProducts.products.add(unasProduct);
    }
    return unasProducts;
  }

  private UnasOrders mapUgyvitelOrdersToUnasOrders(final UgyvitelOrders ugyvitelOrders) {

    final UnasOrders unasOrders = new UnasOrders();

    unasOrders.orders = new ArrayList<>();

    for (final UgyvitelOrder ugyvitelOrder : ugyvitelOrders.order) {
      final UnasOrder unasOrder = new UnasOrder();

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
      unasOrder.addresses.shipping.ZIP = ugyvitelOrder.deliveryAddressZip != null ? ugyvitelOrder.deliveryAddressZip
          : "";
      unasOrder.addresses.shipping.city = ugyvitelOrder.deliveryAddressCity != null ? ugyvitelOrder.deliveryAddressCity
          : "";
      unasOrder.addresses.shipping.country = ugyvitelOrder.deliveryAddressCountry != null
          ? ugyvitelOrder.deliveryAddressCountry
          : "";
      unasOrder.addresses.shipping.countryCode = "";
      unasOrder.addresses.shipping.county = "";
      unasOrder.addresses.shipping.street = "";
      unasOrder.addresses.shipping.streetName = ugyvitelOrder.deliveryAddressStreet != null
          ? ugyvitelOrder.deliveryAddressStreet
          : "";
      unasOrder.addresses.shipping.streetType = ugyvitelOrder.deliveryAddressPublicDomain != null
          ? ugyvitelOrder.deliveryAddressPublicDomain
          : "";
      unasOrder.addresses.shipping.streetNumber = ugyvitelOrder.deliveryAddressNumber != null
          ? ugyvitelOrder.deliveryAddressNumber
          : "";
      // group and params missing
      unasOrder.currency = ugyvitelOrder.currency;
      unasOrder.payment.name = ugyvitelOrder.paymentmethod;

      if (ugyvitelOrder.bottomComment != null) {
        final Comment comment = new Comment();
        comment.text = ugyvitelOrder.bottomComment;
        unasOrder.comments.comments.add(comment);
      }

      if (ugyvitelOrder.topComment != null) {
        final Comment comment = new Comment();
        comment.text = ugyvitelOrder.topComment;
        unasOrder.comments.comments.add(comment);
      }

      for (final Detail detail : ugyvitelOrder.details.detail) {
        final Item item = new Item();
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

  public UnasCustomers mapUgyvitelCustomersToUnasCustomers(final UgyvitelCustomers ugyvitelCustomers) {
    final UnasCustomers unasCustomers = new UnasCustomers();

    unasCustomers.customer = new ArrayList<>();

    for (final UgyvitelCustomer ugyvitelCustomer : ugyvitelCustomers.customer) {

        final UnasCustomer unasCustomer = new UnasCustomer();
        
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
        unasCustomer.addresses.invoice.taxNumber = ugyvitelCustomer.taxNumber;
        unasCustomer.addresses.invoice.euTaxNumber = ugyvitelCustomer.euTaxNumber;
        unasCustomer.addresses.invoice.name = ugyvitelCustomer.centralAddressName != null
            ? ugyvitelCustomer.centralAddressName
            : ugyvitelCustomer.customerName;
        if (ugyvitelCustomer.centralPublicDomain == null) {
          unasCustomer.addresses.invoice.streetName = ugyvitelCustomer.centralStreet;
        } else {
          unasCustomer.addresses.invoice.streetName = ugyvitelCustomer.centralStreet;
          unasCustomer.addresses.invoice.streetType = ugyvitelCustomer.centralPublicDomain;
          unasCustomer.addresses.invoice.streetNumber = ugyvitelCustomer.centralNumber;
        }
        unasCustomer.addresses.shipping.ZIP = ugyvitelCustomer.deliveryZip;
        unasCustomer.addresses.shipping.city = ugyvitelCustomer.deliveryCity;
        unasCustomer.addresses.shipping.country = ugyvitelCustomer.deliveryCountry;
        unasCustomer.addresses.shipping.country = ugyvitelCustomer.deliveryCountryCode != null
            ? ugyvitelCustomer.deliveryCountryCode.toLowerCase()
            : "";
        unasCustomer.addresses.shipping.name = ugyvitelCustomer.deliveryAddressName != null
            ? ugyvitelCustomer.deliveryAddressName
            : ugyvitelCustomer.customerName;
        if (ugyvitelCustomer.deliveryPublicDomain == null) {
          unasCustomer.addresses.shipping.streetName = ugyvitelCustomer.deliveryStreet;
        } else {
          unasCustomer.addresses.shipping.streetName = ugyvitelCustomer.deliveryStreet;
          unasCustomer.addresses.shipping.streetType = ugyvitelCustomer.deliveryPublicDomain;
          unasCustomer.addresses.shipping.streetNumber = ugyvitelCustomer.deliveryNumber;
        }

        if(ugyvitelCustomer.categories != null && ugyvitelCustomer.categories.category != null){
          for (com.ks.supersync.model.ugyvitel.customer.Category category : ugyvitelCustomer.categories.category) {
            if(category.isBaseCategory.equals("false")){
              unasCustomer.group.name = category.categoryValue;
            } else {
              continue;  
            }
            continue; 
          }
        }

        unasCustomers.customer.add(unasCustomer);    
    }
    return unasCustomers;
  }

  private UgyvitelProducts mapUnasProductsToUgyvitelProducts(final UnasProducts unasProducts, final String apiKey) {
    final I18n webshopI18n = i18nRepository.findByWebshopApiKey(apiKey);

    final UgyvitelProducts ugyvitelProducts = new UgyvitelProducts();
    ugyvitelProducts.product = new ArrayList<>();

    for (final UnasProduct unasProduct : unasProducts.products) {
      final UgyvitelProduct ugyvitelProduct = new UgyvitelProduct();
      ugyvitelProduct.productId = 0;
      ugyvitelProduct.webshopId = unasProduct.id;
      ugyvitelProduct.productCode = unasProduct.sku;
      ugyvitelProduct.productName.tName.add(new TranslatedName(webshopI18n.language, unasProduct.name));
      ugyvitelProduct.itemNumber = "";
      if (unasProduct.description != null) {
        ugyvitelProduct.comment.tComment
            .add(new TranslatedComment(webshopI18n.language, unasProduct.description.shortDesc));
      }
      ugyvitelProduct.barCode = "";
      ugyvitelProduct.lastPurchasePrice = "";
      ugyvitelProduct.active = unasProduct.state.equals("live") ? 1 : 0;
      ugyvitelProduct.vatCode = unasProduct.Prices != null ? unasProduct.Prices.vat : "";

      ugyvitelProduct.quantityUnit.tQUnit.add(new TranslatedQuantityUnit(webshopI18n.language, unasProduct.unit));
      ugyvitelProduct.service = unasProduct.stocks != null && unasProduct.stocks.stock != null
          && unasProduct.stocks.status != null ? unasProduct.stocks.status.active : 0;
      if (unasProduct.Prices != null) {
        for (final Price price : unasProduct.Prices.prices) {
          if (price.type.equals("normal")) {
            ugyvitelProduct.unitPrice = price.net != null ? price.net : "";
          } else {
            final PriceRule priceRule = new PriceRule();
            priceRule.priceRuleId = "";
            priceRule.validFrom = price.saleStart;
            priceRule.validTo = price.saleEnd;
            priceRule.priceRuleName = price.type;
            priceRule.currency = "";
            priceRule.price = price.net;
            ugyvitelProduct.priceRules.priceRule.add(priceRule);
          }
        }
      } else {
        ugyvitelProduct.unitPrice = "";
      }
      ugyvitelProducts.product.add(ugyvitelProduct);
    }
    return ugyvitelProducts;
  }

  private UgyvitelCustomers mapUnasCustomersToUgyvitelCustomers(final UnasCustomers unasCustomers) {

    final UgyvitelCustomers ugyvitelCustomers = new UgyvitelCustomers();
    ugyvitelCustomers.customer = new ArrayList<>();

    for (final UnasCustomer unasCustomer : unasCustomers.customer) {
      final UgyvitelCustomer ugyvitelCustomer = new UgyvitelCustomer();
      ugyvitelCustomer.webshopId = unasCustomer.id;
      ugyvitelCustomer.customerName = unasCustomer.contact.name;
      ugyvitelCustomer.email = unasCustomer.email;
      ugyvitelCustomer.phone = unasCustomer.contact != null ? unasCustomer.contact.phone : "";
      if(unasCustomer.addresses != null && unasCustomer.addresses.invoice != null){
        ugyvitelCustomer.taxNumber = unasCustomer.addresses.invoice.taxNumber;
        ugyvitelCustomer.euTaxNumber = unasCustomer.addresses.invoice.euTaxNumber;
      }
      ugyvitelCustomer.centralAddressName = unasCustomer.addresses.invoice.name;
      ugyvitelCustomer.centralCountry = unasCustomer.addresses.invoice.country;
      ugyvitelCustomer.centralZip = unasCustomer.addresses.invoice.ZIP;
      ugyvitelCustomer.centralCity = unasCustomer.addresses.invoice.city;
      ugyvitelCustomer.centralStreet = unasCustomer.addresses.invoice.street;
      ugyvitelCustomer.centralPublicDomain = unasCustomer.addresses.invoice.streetType;
      ugyvitelCustomer.centralStaircase = "";
      ugyvitelCustomer.centralDoor = "";
      ugyvitelCustomer.centralFloor = "";
      ugyvitelCustomer.centralNumber = unasCustomer.addresses.invoice.streetNumber;
      ugyvitelCustomer.centralBuilding = "";
      ugyvitelCustomer.deliveryAddressName = unasCustomer.addresses.shipping.name;
      ugyvitelCustomer.deliveryCountry = unasCustomer.addresses.shipping.country;
      ugyvitelCustomer.deliveryCity = unasCustomer.addresses.shipping.city;
      ugyvitelCustomer.deliveryStreet = unasCustomer.addresses.shipping.street;
      ugyvitelCustomer.deliveryZip = unasCustomer.addresses.shipping.ZIP;
      ugyvitelCustomer.deliveryPublicDomain = unasCustomer.addresses.shipping.streetType;
      ugyvitelCustomer.deliveryStaircase = "";
      ugyvitelCustomer.deliveryDoor = "";
      ugyvitelCustomer.deliveryFloor = "";
      ugyvitelCustomer.deliveryNumber = unasCustomer.addresses.shipping.streetNumber;
      ugyvitelCustomer.deliveryBuilding = "";

      if (unasCustomer.group != null) {

        ugyvitelCustomer.categories.category
            .add(new com.ks.supersync.model.ugyvitel.customer.Category(unasCustomer.group.id, unasCustomer.group.name));

      }

      /*
       * if(ugyvitelCustomer.otherAddresses != null &&
       * ugyvitelCustomer.otherAddresses.otherAddress != null){ for (OtherAddress
       * ugyvitelOtherAddress : ugyvitelCustomer.otherAddresses.otherAddress) { Other
       * unasOtherAddress = new Other();
       * 
       * unasOtherAddress.ZIP = ugyvitelOtherAddress.otherZip; unasOtherAddress.city =
       * ugyvitelOtherAddress.otherCity; unasOtherAddress.country =
       * ugyvitelOtherAddress.otherCountry; unasOtherAddress.name =
       * ugyvitelOtherAddress.otherAddressName; unasOtherAddress.streetName =
       * ugyvitelOtherAddress.otherStreet; unasOtherAddress.streetType =
       * ugyvitelOtherAddress.otherPublicDomain; unasOtherAddress.streetNumber =
       * ugyvitelOtherAddress.otherNumber;
       * 
       * unasCustomer.addresses.others.add(unasOtherAddress); } }
       */

      ugyvitelCustomers.customer.add(ugyvitelCustomer);
    }

    return ugyvitelCustomers;
  }

  private UgyvitelOrders mapUnasOrdersToUgyvitelOrders(final UnasOrders unasOrders) {

    final UgyvitelOrders ugyvitelOrders = new UgyvitelOrders();
    ugyvitelOrders.order = new ArrayList<>();
    for (final UnasOrder unasOrder : unasOrders.orders) {
      final UgyvitelOrder ugyvitelOrder = new UgyvitelOrder();
      ugyvitelOrder.webshopId = unasOrder.key;
      ugyvitelOrder.currency = unasOrder.currency;
      ugyvitelOrder.customerWebshopId = "";
      ugyvitelOrder.customerCode = unasOrder.customer != null ? unasOrder.customer.id : "";
      ugyvitelOrder.Phone = unasOrder.customer != null ? unasOrder.customer.contact.phone : "";
      ugyvitelOrder.Email = unasOrder.customer != null ? unasOrder.customer.email : "";
      ugyvitelOrder.customerName = "";
      ugyvitelOrder.customerContact = unasOrder.customer != null ? unasOrder.customer.contact.name : "";
      ugyvitelOrder.invoicingZip = unasOrder.customer != null && unasOrder.customer.addresses != null
          && unasOrder.customer.addresses.invoice != null ? unasOrder.customer.addresses.invoice.ZIP : "";
      ugyvitelOrder.invoicingCity = unasOrder.customer != null && unasOrder.customer.addresses != null
          && unasOrder.customer.addresses.invoice != null ? unasOrder.customer.addresses.invoice.city : "";
      ugyvitelOrder.invoicingStreet = unasOrder.customer != null && unasOrder.customer.addresses != null
          && unasOrder.customer.addresses.invoice != null ? unasOrder.customer.addresses.invoice.street : "";
      ugyvitelOrder.invoicingStaircase = "";
      ugyvitelOrder.invoicingDoor = "";
      ugyvitelOrder.invoicingFloor = "";
      ugyvitelOrder.invoicingBuilding = "";
      ugyvitelOrder.invoicingNumber = unasOrder.customer != null && unasOrder.customer.addresses != null
          && unasOrder.customer.addresses.invoice != null ? unasOrder.customer.addresses.invoice.streetNumber : "";
      ugyvitelOrder.invoicingPublicDomain = unasOrder.customer != null && unasOrder.customer.addresses != null
          && unasOrder.customer.addresses.invoice != null ? unasOrder.customer.addresses.invoice.streetType : "";
      ugyvitelOrder.invoicingCountry = unasOrder.customer != null && unasOrder.customer.addresses != null
          && unasOrder.customer.addresses.invoice != null ? unasOrder.customer.addresses.invoice.country : "";
      ugyvitelOrder.deliveryName = unasOrder.customer != null && unasOrder.customer.addresses != null
          && unasOrder.customer.addresses.shipping != null ? unasOrder.customer.addresses.shipping.name : "";
      ugyvitelOrder.deliveryZip = ugyvitelOrder.deliveryName = unasOrder.customer != null
          && unasOrder.customer.addresses != null && unasOrder.customer.addresses.shipping != null
              ? unasOrder.customer.addresses.shipping.ZIP
              : "";
      ugyvitelOrder.deliveryCity = ugyvitelOrder.deliveryName = unasOrder.customer != null
          && unasOrder.customer.addresses != null && unasOrder.customer.addresses.shipping != null
              ? unasOrder.customer.addresses.shipping.city
              : "";
      ugyvitelOrder.deliveryStreet = ugyvitelOrder.deliveryName = unasOrder.customer != null
          && unasOrder.customer.addresses != null && unasOrder.customer.addresses.shipping != null
              ? unasOrder.customer.addresses.shipping.street
              : "";
      ugyvitelOrder.deliveryStaircase = "";
      ugyvitelOrder.deliveryDoor = "";
      ugyvitelOrder.deliveryFloor = "";
      ugyvitelOrder.deliveryBuilding = "";
      ugyvitelOrder.deliveryNumber = ugyvitelOrder.deliveryName = unasOrder.customer != null
          && unasOrder.customer.addresses != null && unasOrder.customer.addresses.shipping != null
              ? unasOrder.customer.addresses.shipping.streetNumber
              : "";
      ugyvitelOrder.deliveryPublicDomain = ugyvitelOrder.deliveryName = unasOrder.customer != null
          && unasOrder.customer.addresses != null && unasOrder.customer.addresses.shipping != null
              ? unasOrder.customer.addresses.shipping.streetType
              : "";
      ugyvitelOrder.deliveryCountry = ugyvitelOrder.deliveryName = unasOrder.customer != null
          && unasOrder.customer.addresses != null && unasOrder.customer.addresses.shipping != null
              ? unasOrder.customer.addresses.shipping.country
              : "";
      ugyvitelOrder.taxNumber = ugyvitelOrder.deliveryName = unasOrder.customer != null
          && unasOrder.customer.addresses != null && unasOrder.customer.addresses.shipping != null
              ? unasOrder.customer.addresses.invoice.taxNumber
              : "";
      ugyvitelOrder.sequencePrefix = "";
      ugyvitelOrder.paymentDelayDays = "";
      ugyvitelOrder.paymentmethod = unasOrder.payment != null ? unasOrder.payment.name : "";
      ugyvitelOrder.deliveryDate = "";
      ugyvitelOrder.orderDate = "";
      ugyvitelOrder.transportMode = unasOrder.shipping != null ? unasOrder.shipping.name : "";
      ugyvitelOrder.topComment = "";
      ugyvitelOrder.bottomComment = "";
      if (unasOrder.items.item != null) {
        ugyvitelOrder.details = new Details();
        ugyvitelOrder.details.detail = new ArrayList<>();
        for (final Item item : unasOrder.items.item) {
          final Detail detail = new Detail();
          detail.webshopId = "";
          detail.productCode = item.sku;
          detail.productName = item.name;
          detail.quantity = item.quantity;
          detail.quantityUnit = item.unit;
          detail.unitPrice = item.priceNet;
          detail.stock = "";
          detail.productComment = ""; // ellenőrizni
          detail.vatCode = item.vat;
          ugyvitelOrder.details.detail.add(detail);
        }
      }
      ugyvitelOrders.order.add(ugyvitelOrder);
    }

    return ugyvitelOrders;
  }

  private UnasProducts mapUgyvitelProductsToUnasProducts(final UgyvitelProducts ugyvitelProducts, final String apiKey) {
    final I18n webshopI18n = i18nRepository.findByWebshopApiKey(apiKey);

    final UnasProducts unasProducts = new UnasProducts();

    unasProducts.products = new ArrayList<>();

    for (final UgyvitelProduct ugyvitelProduct : ugyvitelProducts.product) {
      final UnasProduct unasProduct = new UnasProduct();
      unasProduct.Prices = new Prices();
      unasProduct.Prices.prices = new ArrayList<>();
      unasProduct.description = new Description();
      final Price unitPrice = new Price();

      unasProduct.sku = ugyvitelProduct.productCode;
      for (final TranslatedName tName : ugyvitelProduct.productName.tName) {
        if (tName.languageId.equals(webshopI18n.language)) {
          unasProduct.name = tName.name;
        }
      }
      for (final TranslatedQuantityUnit tQuantity : ugyvitelProduct.quantityUnit.tQUnit) {
        if (tQuantity.languageId.equals(webshopI18n.language)) {
          unasProduct.unit = tQuantity.quantityUnit;
        }
      }
      for (final TranslatedComment tComment : ugyvitelProduct.comment.tComment) {
        if (tComment.languageId.equals(webshopI18n.language)) {
          unasProduct.description.shortDesc = tComment.comment;
        }
      }
      unasProduct.description.longDesc = "";
      unitPrice.type = "normal";
      unasProduct.Prices.prices.add(unitPrice);
      final Price unasSalePrice = new Price();
      /* unasSalePrice.type = "sale";
      unasSalePrice.net = ugyvitelProduct.discountPrice;
      unasSalePrice.gross = "13654";
      unasProduct.Prices.prices.add(unasSalePrice); */
      if (ugyvitelProduct.priceRules != null) {
        if (ugyvitelProduct.priceRules.priceRule != null) {
          for (final PriceRule pRule : ugyvitelProduct.priceRules.priceRule) {
            if (pRule.priceRuleName.equals("normal")) {
              unitPrice.gross = String.valueOf(Integer.parseInt(pRule.price) * Integer.parseInt(ugyvitelProduct.vatRate));
              unitPrice.net = pRule.price;
              continue;
            } else {
              if (pRule.price.equals(ugyvitelProduct.discountPrice)) {
                for (final Price unasPrices : unasProduct.Prices.prices) {
                  if (unasPrices.type.equals("discount")) {
                    unasPrices.saleStart = pRule.validFrom;
                    unasPrices.saleEnd = pRule.validTo;
                    unasPrices.net = pRule.price;
                    unasPrices.gross = String.valueOf(Integer.parseInt(pRule.price) * Integer.parseInt(ugyvitelProduct.vatRate));
                  }
                }
              } else {
                final Price unasPrice = new Price();
                unasPrice.type = "other"; // felülvizsgálni
                unasPrice.net = pRule.price; // felülvizsgálni
                unasPrice.gross = String.valueOf(Integer.parseInt(pRule.price) * Integer.parseInt(ugyvitelProduct.vatRate)); // kötelező mező
                unasPrice.start = pRule.validFrom; // felülvizsgálni
                unasPrice.end = pRule.validTo; // felülvizsgálni
                unasProduct.Prices.prices.add(unasPrice);
              }
            }
          }
        }
      } else {
        unasProduct.Prices.prices.add(unitPrice);
      }
      for (final com.ks.supersync.model.ugyvitel.product.Category ugyvitelCatergoy : ugyvitelProduct.categories.category) {
        final Category unasCategory = new Category();
        unasCategory.id = ugyvitelCatergoy.categoryId;
        unasProduct.categories.category.add(unasCategory);
      }
      unasProducts.products.add(unasProduct);
    }
    return unasProducts;
  }

  public void ValidateUgyvitelCustomersToUnas(final UgyvitelCustomers ugyvitelCustomers, final UgyvitelCustomers validatedUgyvitelCustomers, final UgyvitelCustomers invalidUgyvitelCustomers){
    for (final UgyvitelCustomer ugyvitelCustomer : ugyvitelCustomers.customer) {
      if(!ugyvitelCustomer.countryCode.equals("HU") 
      || ugyvitelCustomer.email.equals("") 
      || ugyvitelCustomer.centralAddressName.equals("") 
      || ugyvitelCustomer.centralZip.length() < 4 
      || ugyvitelCustomer.phone.length() < 6
      || ugyvitelCustomer.phone.equals("")
      || ugyvitelCustomer.taxNumber.length() != 11){
        invalidUgyvitelCustomers.customer.add(ugyvitelCustomer);
      } else {
        validatedUgyvitelCustomers.customer.add(ugyvitelCustomer);
      }
    }
  }
}