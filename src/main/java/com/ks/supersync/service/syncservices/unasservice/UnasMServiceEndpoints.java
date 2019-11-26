package com.ks.supersync.service.syncservices.unasservice;

public enum UnasMServiceEndpoints {

    GET_PRODUCTS("/getproducts_fromunas"),
    GET_CUSTOMERS("/getcustomers_fromunas"),
    GET_ORDERS("/getorders_fromunas"),
    SET_PRODUCTS("/setproducts_tounas"),
    SET_CUSTOMERS("/setcustomers_tounas"),
    SET_ORDERS("/setorders_tounas"),
    SET_CATEGORIES("/setcategories_tounas"),
    SET_STOCKS("/setstocks_tounas");

    public final String url;

    UnasMServiceEndpoints(String url) {
        this.url = url;
    }

    public String toString(){
        return url;
    }

}
